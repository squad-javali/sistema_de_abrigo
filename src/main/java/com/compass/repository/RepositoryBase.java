package com.compass.repository;

import com.compass.domain.Entity;

import java.util.Map;

public interface RepositoryBase<T extends Entity, ID> {
    T findById(ID id);
    Map<Integer, T> findAll();
    T save(T entity);
    void delete(T entity);
    void deleteById(ID id);
}
