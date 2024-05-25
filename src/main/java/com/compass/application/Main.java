package com.compass.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.compass.application.controllers.checkout.CheckoutController;
import com.compass.application.controllers.checkout.CheckoutMenu;
import com.compass.domain.CentroDeDistribuicao;
import com.compass.domain.Estoque;
import com.compass.domain.ItemPedido;
import com.compass.domain.Pedido;
import com.compass.domain.Produto;
import com.compass.domain.enums.RoupaSexo;
import com.compass.domain.enums.RoupaTamanho;
import com.compass.services.CentroService;
import com.compass.services.EstoqueService;
import com.compass.services.ItemPedidoService;
import com.compass.services.PedidoService;
import com.compass.services.ProdutoService;
import com.compass.utils.JpaUtil;


public class Main {
    public static void main(String[] args) {

        JpaUtil.init();

        CentroService service = new CentroService();
        CentroDeDistribuicao centro = new CentroDeDistribuicao(null, "Centro de Distribuição Esperança",
                "Av. Boqueirão, 2450 - Igara, Canoas - RS, 92032-420");
        CentroDeDistribuicao centro1 = new CentroDeDistribuicao(null, "Centro de Distribuição Prosperidade",
                "Av. Borges de Medeiros 1501 Porto Alegre CEP 90119900");
        CentroDeDistribuicao centro2 = new CentroDeDistribuicao(null, "Centro de Distribuição Reconstrução",
                "R. Dr. Décio Martins Costa, 312 - Vila Eunice Nova, Cachoeirinha - RS, 94920-170");
        service.save(centro);
        service.save(centro1);
        service.save(centro2);

        PedidoService service2 = new PedidoService();

        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        Pedido p1 = new Pedido(null, centro, LocalDate.parse("20/07/2022",fmt1), false);
        Pedido p2 = new Pedido(null, centro1, LocalDate.parse("13/07/2022",fmt1), false);
        Pedido p3 = new Pedido(null, centro2, LocalDate.parse("10/07/2022",fmt1), true);

        service2.save(p1);
        service2.save(p2);
        service2.save(p3);


        ProdutoService serviceProd = new ProdutoService();

        Produto prod1 = new Produto(null, "Arroz", RoupaTamanho.NULL, RoupaSexo.N);
        serviceProd.save(prod1);


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

        EstoqueService serviceEstoque = new EstoqueService();

        serviceEstoque.save(new Estoque(null, prod1, centro1, 1000, 10000));


        CheckoutMenu menu = new CheckoutMenu(new CheckoutController());

        menu.menu();


    }
}