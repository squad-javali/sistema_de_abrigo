package com.compass.application.controllers.estoque;

import com.compass.domain.CentroDeDistribuicao;
import com.compass.services.CentroService;
import lombok.AllArgsConstructor;

import static com.compass.utils.LeitorDeDados.lerIntInterval;
import static com.compass.utils.LeitorDeDados.selecionarItem;

@AllArgsConstructor
public class EstoqueMenu {

    private EstoqueController estoqueController;
    private CentroService centroService;

    public void menu() {
        CentroDeDistribuicao centro = selecionarItem("Selecione um centro","Centro",centroService.findAll());
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
                    estoqueController.adicionar(centro);
                    break;
                case 2:
                    estoqueController.adicionarProduto(centro);
                    break;
                case 3:
                    estoqueController.removerItens(centro);
                    break;
                case 4:
                    estoqueController.atualizar(centro);
                    break;
                case 5:
                    estoqueController.remover(centro);
                    break;
                case 6:
                    estoqueController.listar(centro.getId());
                    break;
                default:
                    break loop;
            }
        }
    }
}