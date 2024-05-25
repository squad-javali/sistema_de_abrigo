package com.compass.application.controllers.checkout;

import static com.compass.utils.LeitorDeDados.*;

import java.util.List;

import com.compass.domain.ItemPedido;
import com.compass.domain.Pedido;
import com.compass.utils.LeitorDeDados;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckoutMenu {

    private CheckoutController checkoutController;

    public void menu() {

        while (true) {
            System.out.println("Checkout de Itens");
            
            Pedido pedido = checkoutController.listarPedidosPendentes();
           

            System.out.println("Digite o id do Pedido desejado: ");
            int opcao = lerInt("Opção inválida");


            List<ItemPedido> listaDeItens = checkoutController.listarItensPedido(opcao);

            System.out.println("Aceitar pedido? ");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int resp = lerIntInterval("Opção inválida", 1, 2);  

            if (resp == 1) {
                checkoutController.abaterEstoque(listaDeItens, pedido);
                
				// metodo para armazenar a qtde de itens enviados
            }
            else if (resp == 2) {
                String motivo = lerString("Descreva o motivo da recusa");
                checkoutController.motivoRecusa(motivo);
            }

            
			

            }
        }
    }

