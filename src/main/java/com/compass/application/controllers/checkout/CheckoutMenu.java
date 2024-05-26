package com.compass.application.controllers.checkout;

import com.compass.domain.CentroDeDistribuicao;
import com.compass.domain.ItemPedido;
import com.compass.domain.Pedido;
import com.compass.domain.exceptions.NoItemsRegisteredException;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.compass.utils.LeitorDeDados.lerIntInterval;
import static com.compass.utils.LeitorDeDados.lerString;

@AllArgsConstructor
public class CheckoutMenu {

    private CheckoutController checkoutController;

    public void menu(CentroDeDistribuicao centro) {
        System.out.println("Checkout de Itens");
        Pedido pedido;
        try {
            pedido = checkoutController.listarPedidosPendentes(centro.getId());
        } catch (NoItemsRegisteredException e) {
            System.out.println("Nada foi pedido");
            return;
        }

        List<ItemPedido> listaDeItens = checkoutController.listarItensPedido(pedido.getId());

        System.out.println("Aceitar pedido? ");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        System.out.print("Selecione uma opção:");
        int resp = lerIntInterval("Opção inválida", 1, 2);

        if (resp == 1) {
            checkoutController.abaterEstoque(listaDeItens, pedido);
        } else if (resp == 2) {
            String motivo = lerString("Descreva o motivo da recusa");
            checkoutController.motivoRecusa(motivo, pedido);
        }
    }
}

