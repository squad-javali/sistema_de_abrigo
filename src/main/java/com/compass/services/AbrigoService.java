package com.compass.services;

import com.compass.domain.Abrigo;
import com.compass.repository.AbrigoRepository;

import java.util.Map;

public class AbrigoService {
    private final AbrigoRepository repository = new AbrigoRepository();

    public void save(Abrigo abrigo) {
        repository.save(abrigo);
    }

    public Abrigo findById(Integer id) {
        return repository.findById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Map<Integer, Abrigo> findAll() {
        return repository.findAll();
    }

}