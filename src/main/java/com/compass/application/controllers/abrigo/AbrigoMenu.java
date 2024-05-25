package com.compass.application.controllers.estoque;

import com.compass.application.controllers.abrigo.AbrigoController;
import lombok.AllArgsConstructor;

import static com.compass.utils.LeitorDeDados.lerIntInterval;
@AllArgsConstructor
public class AbrigoMenu {

    private AbrigoController abrigoController;

    public void menu(){
        loop:
        while (true) {
            System.out.println("Abrigo");
            System.out.println("1 - Cadastrar abrigo");
            System.out.println("2 - Atualizar abrigo");
            System.out.println("3 - Excluir abrigo");
            System.out.println("4 - Listar abrigo");
            System.out.println("5 - Voltar");
            System.out.print("Digite a opção desejada: ");
            int opcao = lerIntInterval("Opção Invalida: ", 1, 5);
            switch (opcao) {
                case 1:
                    abrigoController.cadastrarAbrigo();
                    break;
                case 2:
                    abrigoController.atualizarAbrigo();
                    break;
                case 3:
                    abrigoController.deletarAbrigo();
                    break;
                case 4:
                    abrigoController.listarTodosAbrigos();
                    break;
                default:
                    break loop;
            }
        }
    }
}