package com.compass.application.controllers.centrodedistribuicao;

import com.compass.application.controllers.checkout.CheckoutController;
import com.compass.application.controllers.checkout.CheckoutMenu;
import com.compass.application.controllers.estoque.EstoqueController;
import com.compass.application.controllers.estoque.EstoqueMenu;
import com.compass.domain.CentroDeDistribuicao;
import com.compass.services.*;
import com.compass.utils.LeitorDeDados;
import lombok.AllArgsConstructor;

import static com.compass.utils.LeitorDeDados.selecionarItem;

@AllArgsConstructor
public class CentroMenu {

    private CentroService centroService;

    public void menu() {
        CentroDeDistribuicao centro = selecionarItem("Selecione um centro","Centro",centroService.findAll());
        int opt;
        loop:
        while (true) {
            System.out.println("1 - Gerenciar Estoque");
            System.out.println("2 - Checkout de pedido");
            System.out.println("3 - Voltar");
            opt = LeitorDeDados.lerIntInterval("Opção inválida! Digite novamente: ", 1, 3);
            switch (opt) {
                case 1:
                    EstoqueMenu estoqueMenu = new EstoqueMenu(new EstoqueController(new EstoqueService(), new ProdutoService()));
                    estoqueMenu.menu(centro);
                    break;
                case 2:
                    CheckoutMenu checkoutMenu = new CheckoutMenu(new CheckoutController(new PedidoService(), new ItemPedidoService(), new EstoqueService()));
                    checkoutMenu.menu(centro);
                    break;
                case 3:
                    break loop;
            }
        }
    }
}