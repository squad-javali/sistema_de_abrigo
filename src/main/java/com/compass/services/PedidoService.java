package com.compass.services;

import com.compass.domain.Pedido;
import com.compass.repository.PedidoRepository;

import java.util.Map;

public class PedidoService {
    private final PedidoRepository repository = new PedidoRepository();

    public void save(Pedido Pedido) {
        repository.save(Pedido);
    }

    public Pedido findById(Integer id) {
        return repository.findById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Map<Integer, Pedido> findAll() {
        return repository.findAll();
    }

}