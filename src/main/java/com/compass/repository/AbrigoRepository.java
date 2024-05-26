package com.compass.repository;


import com.compass.domain.Abrigo;
import com.compass.domain.exceptions.DbIntegrityException;
import com.compass.domain.exceptions.EntityExistsException;
import com.compass.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.RollbackException;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AbrigoRepository implements SimpleRepository<Abrigo, Integer> {

    private final EntityManager entityManager;

    public AbrigoRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public Abrigo findById(Integer id) {
        return entityManager.find(Abrigo.class, id);
    }

    @Override
    public Map<Integer, Abrigo> findAll() {
        List<Abrigo> abrigos = entityManager.createQuery("SELECT a FROM Abrigo a", Abrigo.class).getResultList();
        return abrigos.stream().collect(Collectors.toMap(Abrigo::getId, Function.identity()));
    }

    @Override
    public Abrigo save(Abrigo abrigo) throws EntityExistsException {
        try {
            entityManager.getTransaction().begin();
            if (abrigo.getId() == null) {
                entityManager.persist(abrigo);
            } else {
                abrigo = entityManager.merge(abrigo);
            }
            entityManager.getTransaction().commit();
            return abrigo;
        } catch (ConstraintViolationException e) {
            throw new EntityExistsException("Produto já cadastrado");
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public void delete(Abrigo abrigo) throws DbIntegrityException {
        try {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(abrigo)) {
                abrigo = entityManager.merge(abrigo);
            }
            entityManager.remove(abrigo);
            entityManager.getTransaction().commit();
        } catch (RollbackException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteById(Integer id) throws DbIntegrityException {
        Abrigo abrigo = findById(id);
        if (abrigo != null) {
            delete(abrigo);
        }
    }
}
