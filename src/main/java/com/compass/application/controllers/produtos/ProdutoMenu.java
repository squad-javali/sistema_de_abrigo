package com.compass.application.controllers.produtos;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import static com.compass.utils.LeitorDeDados.lerIntInterval;

@AllArgsConstructor
@RequiredArgsConstructor
public class ProdutoMenu {

    private ProdutoController produtoController;

    public void menu() {
        loop: while (true){
            System.out.println("Produto Menu");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Remover Produto");
            System.out.println("3 - Atualizar Produto");
            System.out.println("4 - Listar Produtos");
            System.out.println("5 - Voltar");
            System.out.println("Digite a opção desejada: ");
            int opcao = lerIntInterval("Opcão Invalida: ", 1, 5);
            switch (opcao) {
                case 1:
                    produtoController.adicionar();
                    break;
                case 2:
                    produtoController.remover();
                    break;
                case 3:
                    produtoController.atualizar();
                    break;
                case 4:
                    produtoController.listar();
                    break;
                default:
                    break loop;
            }
        }
    }
}
