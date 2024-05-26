package com.compass.application.controllers.checkout;

import com.compass.domain.Estoque;
import com.compass.domain.ItemPedido;
import com.compass.domain.Pedido;
import com.compass.services.EstoqueService;
import com.compass.services.ItemPedidoService;
import com.compass.services.PedidoService;
import com.compass.utils.LeitorDeDados;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

import static com.compass.utils.LeitorDeDados.imprimirMap;

@AllArgsConstructor
public class CheckoutController {

    private PedidoService pedidoService;
    private ItemPedidoService itemPedidoService;
    private EstoqueService estoqueService;


    public Pedido listarPedidosPendentes(Integer id) {
        return LeitorDeDados.selecionarItem("Selecione um pedido", "Pedido", pedidoService.findByCentroId(id));
    }

    public java.util.List<ItemPedido> listarItensPedido(int pedidoId) {
        java.util.Map<Integer, ItemPedido> list = itemPedidoService.findByPedidoId(pedidoId);
        imprimirMap(list);
        return new ArrayList<>(list.values());
    }

    public void abaterEstoque(java.util.List<ItemPedido> lista, Pedido pedido) {
        try {
            for (ItemPedido itemPedido : lista) {
                Estoque estoque = estoqueService.findByProdutoId(itemPedido.getProduto().getId());
                int qtde = estoque.getQuantidade() - itemPedido.getQuantidade();
                estoque.setQuantidade(qtde);
                estoqueService.save(estoque);
            }
            pedido.setAceite(true);
            pedidoService.save(pedido);
        } catch (NoResultException e) {
            System.out.println("O centro não tem estoque");
        }
    }


    public void motivoRecusa(String motivo, Pedido pedido) {
        pedido.setMotivoRecusa(motivo);
        pedidoService.save(pedido);
    }

    // metodo para armazenar a qtde de itens enviados

}
