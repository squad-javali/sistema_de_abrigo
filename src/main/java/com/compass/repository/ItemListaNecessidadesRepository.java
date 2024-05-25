package com.compass.repository;

import com.compass.domain.Entity;
import com.compass.domain.ItemListaNecessidades;
import com.compass.domain.exceptions.DBException;
import com.compass.utils.UtilJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.RollbackException;

import java.util.Map;

public class ItemListaNecessidadesRepository implements RepositoryBase<ItemListaNecessidades, Integer> {

    private EntityManager manager;
    private EntityTransaction transaction;

    public ItemListaNecessidadesRepository(){
        this.manager = UtilJPA.getManager();
        this.transaction = manager.getTransaction();
    }

    @Override
    public ItemListaNecessidades findById(Integer id) {
        return manager.find(ItemListaNecessidades.class, id);
    }

    @Override
    public ItemListaNecessidades save(ItemListaNecessidades item) {
        try {
            transaction.begin();
            if(item.getId() == null) {
            manager.persist(item);
            } else {
                manager.merge(item);
            }

            return item;


        } catch (RollbackException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void delete(ItemListaNecessidades item) {
        try {
        transaction.begin();
        if(!manager.contains(item)){ item = manager.merge(item); } else { manager.remove(item); }
        transaction.commit();
    }catch (RollbackException e){
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        ItemListaNecessidades item = findById(id);
        if (item != null) {
            delete(item);
        }
    }

    @Override
    public Map<Integer, ItemListaNecessidades> findAll() {
        return Map.of();
    }
}
