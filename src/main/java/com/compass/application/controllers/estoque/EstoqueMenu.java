package com.compass.application.controllers.estoque;

import lombok.AllArgsConstructor;

import static com.compass.utils.LeitorDeDados.lerIntInterval;
@AllArgsConstructor
public class EstoqueMenu {
    private EstoqueController estoqueController;
    public void menu() {
        loop:
        while (true) {
            System.out.println("Estoque");
            System.out.println("1 - Cadastrar estoque");
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
