package com.compass.repository;

import java.util.Map;

public interface SimpleRepository<T, ID> {
    T findById(ID id);
    Map<Integer, T> findAll();
    T save(T entity);
    void delete(T entity);
    void deleteById(ID id);
}
