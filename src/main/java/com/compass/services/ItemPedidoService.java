package com.compass.services;

import com.compass.domain.ItemPedido;
import com.compass.repository.ItemPedidoRepository;

import java.util.Map;

public class ItemPedidoService {
    private final ItemPedidoRepository repository = new ItemPedidoRepository();

    public void save(ItemPedido ItemPedido) {
        repository.save(ItemPedido);
    }

    public ItemPedido findById(Integer id) {
        return repository.findById(id);
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Map<Integer, ItemPedido> findAll() {
        return repository.findAll();
    }

    public Map<Integer, ItemPedido> findByPedidoId(Integer id) {
        return repository.findByPedidoId(id);
    }

}