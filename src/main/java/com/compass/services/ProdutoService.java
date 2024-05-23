package com.compass.services;

import com.compass.domain.Produto;
import com.compass.repository.ProdutoRepository;

import java.util.Map;

public class ProdutoService {
    private final ProdutoRepository repository = new ProdutoRepository();

    public void save(Produto produto) {
        repository.save(produto);
    }

    public Produto findById(Integer id) {
        return repository.findById(id);
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Map<Integer,Produto> findAll() {
        return repository.findAll();
    }

}
