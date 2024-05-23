package com.compass.repository;

import com.compass.domain.CentroDeDistribuicao;
import com.compass.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CentroRepository implements SimpleRepository<CentroDeDistribuicao, Integer> {

    private final EntityManager entityManager;

    public CentroRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public CentroDeDistribuicao findById(Integer id) {
        return entityManager.find(CentroDeDistribuicao.class, id);
    }

    @Override
    public Map<Integer, CentroDeDistribuicao> findAll() {
        List<CentroDeDistribuicao> centros = entityManager.createQuery("SELECT c FROM CentroDeDistribuicao c", CentroDeDistribuicao.class).getResultList();
        return centros.stream().collect(Collectors.toMap(CentroDeDistribuicao::getId, Function.identity()));
    }

    @Override
    public CentroDeDistribuicao save(CentroDeDistribuicao centro) {
        entityManager.getTransaction().begin();
        if (centro.getId() == null) {
            entityManager.persist(centro);
        } else {
            centro = entityManager.merge(centro);
        }
        entityManager.getTransaction().commit();
        return centro;
    }

    @Override
    public void delete(CentroDeDistribuicao centro) {
        entityManager.getTransaction().begin();
        if (!entityManager.contains(centro)) {
            centro = entityManager.merge(centro);
        }
        entityManager.remove(centro);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteById(Integer id) {
        CentroDeDistribuicao centro = findById(id);
        if (centro != null) {
            delete(centro);
        }
    }
}
