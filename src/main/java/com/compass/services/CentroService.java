package com.compass.services;

import com.compass.domain.CentroDeDistribuicao;
import com.compass.repository.CentroRepository;

import java.util.Map;

public class CentroService {
    private final CentroRepository repository = new CentroRepository();

    public void save(CentroDeDistribuicao centro) {
        repository.save(centro);
    }

    public CentroDeDistribuicao findById(Integer id) {
        return repository.findById(id);
    }

    public void update(CentroDeDistribuicao centro) {
        repository.save(centro);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Map<Integer,CentroDeDistribuicao> findAll() {
        return repository.findAll();
    }
}
