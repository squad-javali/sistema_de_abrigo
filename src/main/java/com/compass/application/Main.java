package com.compass.application;

import com.compass.application.controllers.abrigo.AbrigoController;
import com.compass.application.controllers.abrigo.AbrigoMenu;
import com.compass.application.controllers.centrodedistribuicao.CentroMenu;
import com.compass.application.controllers.produtos.ProdutoController;
import com.compass.application.controllers.produtos.ProdutoMenu;
import com.compass.services.AbrigoService;
import com.compass.services.CentroService;
import com.compass.services.ProdutoService;
import com.compass.utils.JpaUtil;
import com.compass.utils.LeitorDeDados;


public class Main {
    public static void main(String[] args) {
        JpaUtil.init();
        int opt;
        loop:
        while (true) {
            System.out.println("1 - Gerenciar Centros de Distribuição");
            System.out.println("2 - Gerenciar Produtos");
            System.out.println("3 - Gerenciar Abrigo");
            System.out.println("4 - Sair");
            System.out.print("Digite a opção desejada: ");
            opt = LeitorDeDados.lerIntInterval("Opção inválida! Digite novamente: ", 1, 4);
            switch (opt) {
                case 1:
                    CentroMenu centroMenu = new CentroMenu(new CentroService());
                    centroMenu.menu();
                    break;
                case 2:
                    ProdutoMenu produtoMenu = new ProdutoMenu(new ProdutoController(new ProdutoService()));
                    produtoMenu.menu();
                    break;
                case 3:
                    AbrigoMenu abrigoMenu = new AbrigoMenu(new AbrigoController(new AbrigoService()));
                    abrigoMenu.menu();
                    break;
                default:
                    LeitorDeDados.close();
                    JpaUtil.close();
                    break loop;
            }
        }
    }
}