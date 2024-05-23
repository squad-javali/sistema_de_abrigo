package com.compass.repository;

import com.compass.domain.Produto;
import com.compass.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProdutoRepository implements SimpleRepository<Produto, Integer> {

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
    public Produto save(Produto produto) {
        entityManager.getTransaction().begin();
        if (produto.getId() == null) {
            entityManager.persist(produto);
        } else {
            produto = entityManager.merge(produto);
        }
        entityManager.getTransaction().commit();
        return produto;
    }

    @Override
    public void delete(Produto produto) {
        entityManager.getTransaction().begin();
        if (!entityManager.contains(produto)) {
            produto = entityManager.merge(produto);
        }
        entityManager.remove(produto);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteById(Integer id) {
        Produto produto = findById(id);
        if (produto != null) {
            delete(produto);
        }
    }
}