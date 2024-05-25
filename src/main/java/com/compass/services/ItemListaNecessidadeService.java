package com.compass.services;

import com.compass.domain.ItemListaNecessidades;
import com.compass.repository.ItemListaNecessidadesRepository;
import com.compass.repository.RepositoryBase;

public class ItemListaNecessidadeService {
    private ItemListaNecessidadesRepository repository = new ItemListaNecessidadesRepository();

    public void save(ItemListaNecessidades item) {
        this.repository.save(item);
    }

    public void delete(ItemListaNecessidades item) {
        this.repository.delete(item);
    }

    public void findById(Integer id) {
        this.repository.findById(id);
    }

    public void deleteById(Integer id){
        this.repository.deleteById(id);
    }
}
