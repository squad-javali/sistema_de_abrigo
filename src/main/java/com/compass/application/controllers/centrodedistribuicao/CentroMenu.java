package com.compass.application.controllers.centrodedistribuicao;

import com.compass.domain.CentroDeDistribuicao;
import com.compass.services.CentroService;
import lombok.AllArgsConstructor;

import static com.compass.utils.LeitorDeDados.lerIntInterval;
import static com.compass.utils.LeitorDeDados.selecionarItem;

@AllArgsConstructor
public class CentroMenu {

    private CentroController centroController;
    private CentroService centroService;

    public void menu() {
        CentroDeDistribuicao centro = selecionarItem("Selecione um centro", "Centro", centroService.findAll());
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
                    centroController.adicionar(centro);
                    break;
                case 2:
                    centroController.adicionarProduto();
                    break;
                case 3:
                    centroController.removerItens(centro);
                    break;
                case 4:
                    centroController.atualizar();
                    break;
                case 5:
                    centroController.remover();
                    break;
                case 6:
                    centroController.listar(centro.getId());
                    break;
                default:
                    break loop;
            }
        }
    }
}