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
/*
        CentroService service = new CentroService();
        AbrigoService abrigoService = new AbrigoService();
        Abrigo abrigo = abrigoService.findById(2);
        CentroDeDistribuicao centro = service.findById(1);
        CentroDeDistribuicao centro1 = service.findById(2);
        CentroDeDistribuicao centro2 = service.findById(3);
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        PedidoService service2 = new PedidoService();

        Pedido p1 = new Pedido(null, abrigo, centro, LocalDate.parse("20/07/2022", fmt1), false, "as");
        Pedido p2 = new Pedido(null, abrigo, centro1, LocalDate.parse("13/07/2022", fmt1), false, "as");
        Pedido p3 = new Pedido(null, abrigo, centro2, LocalDate.parse("10/07/2022", fmt1), true, "as");

        service2.save(p1);
        service2.save(p2);
        service2.save(p3);
        ProdutoService serviceProd = new ProdutoService();

        Produto prod1 = serviceProd.findById(1);


        ItemPedidoService service3 = new ItemPedidoService();

        ItemPedido i1 = new ItemPedido(null, p1, prod1, 10);
        ItemPedido i2 = new ItemPedido(null, p1, prod1, 2);
        ItemPedido i3 = new ItemPedido(null, p1, prod1, 3);
        ItemPedido i4 = new ItemPedido(null, p2, prod1, 15);
        ItemPedido i5 = new ItemPedido(null, p2, prod1, 20);
        ItemPedido i6 = new ItemPedido(null, p2, prod1, 7);
        ItemPedido i7 = new ItemPedido(null, p3, prod1, 5);
        ItemPedido i8 = new ItemPedido(null, p3, prod1, 9);
        ItemPedido i9 = new ItemPedido(null, p3, prod1, 1);
        service3.save(i1);
        service3.save(i2);
        service3.save(i3);
        service3.save(i4);
        service3.save(i5);
        service3.save(i6);
        service3.save(i7);
        service3.save(i8);
        service3.save(i9);
*/
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