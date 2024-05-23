package com.compass.application.controllers.checkout;

public class CheckoutMenu {

    private CheckoutController estoqueController;
    public void menu() {
        loop:
        while (true) {
            System.out.println("Estoque");
            System.out.println("1 - Consultar pedidos");
            System.out.println("2 - Alterar estoque");
            System.out.println("3 - Excluir estoque");
            System.out.println("4 - Listar estoque");
            System.out.println("5 - Voltar");
            System.out.println("Digite a opção desejada: ");
            int opcao = lerIntInterval("Opção Invalida: ", 1, 5);
            switch (opcao) {
                case 1:
                    estoqueController.adicionar();
                    break;
                case 2:
                    estoqueController.atualizar();
                    break;
                case 3:
                    estoqueController.remover();
                    break;
                case 4:
                    estoqueController.listar();
                    break;
                case 5:
                    break loop;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
