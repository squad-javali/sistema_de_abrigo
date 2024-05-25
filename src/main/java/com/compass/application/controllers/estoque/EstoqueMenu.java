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
            System.out.println("2 - Adicionar Produto ao Estoque");
            System.out.println("3 - Transferir produto");
            System.out.println("4 - Alterar estoque");
            System.out.println("5 - Excluir estoque");
            System.out.println("6 - Listar estoque");
            System.out.println("7 - Voltar");
            System.out.print("Digite a opção desejada: ");
            int opcao = lerIntInterval("Opção Invalida: ", 1, 7);
            switch (opcao) {
                case 1:
                    estoqueController.adicionar();
                    break;
                case 2:
                    estoqueController.adicionarProduto();
                    break;
                case 3:
                    estoqueController.removerItens();
                    break;
                case 4:
                    estoqueController.atualizar();
                    break;
                case 5:
                    estoqueController.remover();
                    break;
                case 6:
                    estoqueController.listar();
                    break;
                default:
                    break loop;
            }
        }
    }
}