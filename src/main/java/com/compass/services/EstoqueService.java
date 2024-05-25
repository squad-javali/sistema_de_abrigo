package com.compass.services;

import com.compass.domain.Estoque;
import com.compass.repository.EstoqueRepository;

import java.util.Map;

public class EstoqueService {
    private final EstoqueRepository repository = new EstoqueRepository();

    public void save(Estoque estoque) {
        repository.save(estoque);
    }

    public Estoque findById(Integer id) {
        return repository.findById(id);
    }

    public void update(Estoque estoque) {
        repository.save(estoque);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Map<Integer, Estoque> findAll() {
        return repository.findAll();
    }

    public Map<Integer, Estoque> findAllByCentroId(Integer id) {
        return repository.findAllByCentroId(id);
    }

    public Estoque findByProdutoId(Integer id) {
        return repository.findByProdutoId(id);
    }
}