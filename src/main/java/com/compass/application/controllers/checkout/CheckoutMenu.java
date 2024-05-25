package com.compass.application.controllers.checkout;

import com.compass.domain.ItemPedido;
import com.compass.domain.Pedido;
import com.compass.domain.exceptions.NoItemsRegisteredException;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.compass.utils.LeitorDeDados.*;

@AllArgsConstructor
public class CheckoutMenu {

    private CheckoutController checkoutController;

    public void menu() {

        while (true) {
            System.out.println("Checkout de Itens");
            Pedido pedido;
            try {
                pedido = checkoutController.listarPedidosPendentes();
            } catch (NoItemsRegisteredException e) {
                System.out.println("Nada foi pedido");
                return;
            }

            List<ItemPedido> listaDeItens = checkoutController.listarItensPedido(pedido.getId());

            System.out.println("Aceitar pedido? ");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int resp = lerIntInterval("Opção inválida", 1, 2);

            if (resp == 1) {
                checkoutController.abaterEstoque(listaDeItens, pedido);

                // metodo para armazenar a qtde de itens enviados
            } else if (resp == 2) {
                String motivo = lerString("Descreva o motivo da recusa");
                checkoutController.motivoRecusa(motivo, pedido);
            }

        }
    }
}

