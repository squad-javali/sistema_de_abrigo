package com.compass.repository;

import com.compass.domain.Produto;
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

public class AbrigoRepository implements SimpleRepository<Produto, Integer> {

    private final EntityManager entityManager;

    public ProdutoRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public Produto findById(Integer id) {
        return entityManager.find(Produto.class, id);
    }

    @Override
    public Map<Integer, Produto> findAll() {
        List<Produto> produtos = entityManager.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
        return produtos.stream().collect(Collectors.toMap(Produto::getId, Function.identity()));
    }

    @Override
    public Produto save(Produto produto) throws EntityExistsException {
        try {
            entityManager.getTransaction().begin();
            if (produto.getId() == null) {
                entityManager.persist(produto);
            } else {
                produto = entityManager.merge(produto);
            }
            entityManager.getTransaction().commit();
            return produto;
        } catch (ConstraintViolationException e) {
            throw new EntityExistsException("Produto já cadastrado");
        }
    }

    @Override
    public void delete(Produto produto) throws DbIntegrityException {
        try {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(produto)) {
                produto = entityManager.merge(produto);
            }
            entityManager.remove(produto);
            entityManager.getTransaction().commit();
        } catch (RollbackException e) {
            throw new DbIntegrityException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) throws DbIntegrityException {
        Produto produto = findById(id);
        if (produto != null) {
            delete(produto);
        }
    }
}
