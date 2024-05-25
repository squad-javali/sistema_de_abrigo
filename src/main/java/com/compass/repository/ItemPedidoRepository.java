package com.compass.repository;

import com.compass.domain.ItemPedido;
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

public class ItemPedidoRepository implements SimpleRepository<ItemPedido, Integer> {

    private final EntityManager entityManager;

    public ItemPedidoRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public ItemPedido findById(Integer id) {
        return entityManager.find(ItemPedido.class, id);
    }

    @Override
    public Map<Integer, ItemPedido> findAll() {
        List<ItemPedido> ItemPedidos = entityManager.createQuery("SELECT i FROM ItemPedido i", ItemPedido.class).getResultList();
        return ItemPedidos.stream().collect(Collectors.toMap(ItemPedido::getId, Function.identity()));
    }

   
    public Map<Integer, ItemPedido> findByPedidoId(Integer pedidoId) {
        List<ItemPedido> ItemPedidos = entityManager.createQuery("SELECT i FROM ItemPedido i WHERE i.pedido.id = :pedidoId", ItemPedido.class)
        .setParameter("pedidoId", pedidoId).getResultList();
        return ItemPedidos.stream().collect(Collectors.toMap(ItemPedido::getId, Function.identity()));
    }

    @Override
    public ItemPedido save(ItemPedido ItemPedido) throws EntityExistsException {
        try {
            entityManager.getTransaction().begin();
            if (ItemPedido.getId() == null) {
                entityManager.persist(ItemPedido);
            } else {
                ItemPedido = entityManager.merge(ItemPedido);
            }
            entityManager.getTransaction().commit();
            return ItemPedido;
        } catch (ConstraintViolationException e) {
            throw new EntityExistsException("ItemPedido já cadastrado");
        }
    }

    @Override
    public void delete(ItemPedido ItemPedido) throws DbIntegrityException {
        try {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(ItemPedido)) {
                ItemPedido = entityManager.merge(ItemPedido);
            }
            entityManager.remove(ItemPedido);
            entityManager.getTransaction().commit();
        } catch (RollbackException e) {
            throw new DbIntegrityException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) throws DbIntegrityException {
        ItemPedido ItemPedido = findById(id);
        if (ItemPedido != null) {
            delete(ItemPedido);
        }
    }
}