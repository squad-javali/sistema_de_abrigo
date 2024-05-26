package com.compass.repository;

import com.compass.domain.Pedido;
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

public class PedidoRepository implements SimpleRepository<Pedido, Integer> {

    private final EntityManager entityManager;

    public PedidoRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public Pedido findById(Integer id) {
        return entityManager.find(Pedido.class, id);
    }

    @Override
    public Map<Integer, Pedido> findAll() {
        List<Pedido> Pedidos = entityManager.createQuery("SELECT p FROM Pedido p WHERE p.aceite = false", Pedido.class).getResultList();
        return Pedidos.stream().collect(Collectors.toMap(Pedido::getId, Function.identity()));
    }

    public Map<Integer, Pedido> findByCentroId(Integer centroId) {
        List<Pedido> pedidos = entityManager.createQuery("SELECT p FROM Pedido p WHERE p.aceite = false AND p.centro.id = :centroId", Pedido.class)
                .setParameter("centroId", centroId)
                .getResultList();
        return pedidos.stream().collect(Collectors.toMap(Pedido::getId, Function.identity()));
    }

    @Override
    public Pedido save(Pedido Pedido) throws EntityExistsException {
        try {
            entityManager.getTransaction().begin();
            if (Pedido.getId() == null) {
                entityManager.persist(Pedido);
            } else {
                Pedido = entityManager.merge(Pedido);
            }
            entityManager.getTransaction().commit();
            return Pedido;
        } catch (ConstraintViolationException e) {
            throw new EntityExistsException("Pedido já cadastrado");
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public void delete(Pedido Pedido) throws DbIntegrityException {
        try {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(Pedido)) {
                Pedido = entityManager.merge(Pedido);
            }
            entityManager.remove(Pedido);
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
        Pedido Pedido = findById(id);
        if (Pedido != null) {
            delete(Pedido);
        }
    }
}