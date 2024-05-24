package com.compass.repository;

import com.compass.domain.exceptions.DbIntegrityException;
import com.compass.domain.Estoque;
import com.compass.domain.exceptions.EntityExistsException;
import com.compass.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EstoqueRepository implements SimpleRepository<Estoque, Integer> {

    private final EntityManager entityManager;

    public EstoqueRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public Estoque findById(Integer id) {
        return entityManager.find(Estoque.class, id);
    }

    @Override
    public Map<Integer, Estoque> findAll() {
        List<Estoque> estoques = entityManager.createQuery("SELECT e FROM Estoque e", Estoque.class).getResultList();
        return estoques.stream().collect(Collectors.toMap(Estoque::getId, Function.identity()));
    }

    public Map<Integer, Estoque> findAllByCentroId(Integer centroId) {
        List<Estoque> estoques = entityManager.createQuery(
                        "SELECT e FROM Estoque e WHERE e.centro.id = :centroId", Estoque.class)
                .setParameter("centroId", centroId)
                .getResultList();
        return estoques.stream().collect(Collectors.toMap(Estoque::getId, Function.identity()));
    }

    @Override
    public Estoque save(Estoque estoque) throws EntityExistsException {
        try {
            entityManager.getTransaction().begin();
            if (estoque.getId() == null) {
                entityManager.persist(estoque);
            } else {
                estoque = entityManager.merge(estoque);
            }
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw new EntityExistsException("Estoque com este produto e centro de distribuição já existe.");
        }
        return estoque;
    }

    @Override
    public void delete(Estoque estoque) throws DbIntegrityException {
        try {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(estoque)) {
                estoque = entityManager.merge(estoque);
            }
            entityManager.remove(estoque);
            entityManager.getTransaction().commit();
        } catch (RollbackException e) {
            throw new DbIntegrityException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) throws DbIntegrityException {
        Estoque estoque = findById(id);
        if (estoque != null) {
            delete(estoque);
        }
    }
}
