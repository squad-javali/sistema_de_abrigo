package com.compass.application.controllers.produtos;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.compass.utils.LeitorDeDados.lerIntInterval;
@RequiredArgsConstructor
public class ProdutoMenu {

    @NonNull
    private ProdutoController produtoController;

    public void menu() {
        loop: while (true){
            System.out.println("Produto Menu");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Remover Produto");
            System.out.println("3 - Atualizar Produto");
            System.out.println("4 - Listar Produtos");
            System.out.println("5 - Voltar");
            System.out.print("Digite a opção desejada: ");
            int opcao = lerIntInterval("Opção Invalida: ", 1, 5);
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