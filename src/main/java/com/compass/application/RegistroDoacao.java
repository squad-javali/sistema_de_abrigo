package com.compass.application;

import com.compass.application.controllers.centrodedistribuicao.CentroControle;
import com.compass.application.controllers.estoque.EstoqueController;
import com.compass.application.controllers.estoque.EstoqueMenu;
import com.compass.application.controllers.produtos.ProdutoController;
import com.compass.application.controllers.produtos.ProdutoMenu;
import com.compass.services.CentroService;
import com.compass.services.EstoqueService;
import com.compass.services.ProdutoService;
import com.compass.utils.JpaUtil;
import com.compass.utils.LeitorDeDados;

public class RegistroDoacao {
    public static void menu() {
        JpaUtil.init();
/*        CentroService service = new CentroService();
        CentroDeDistribuicao centro = new CentroDeDistribuicao(null, "Centro de Distribuição Esperança",
                "Av. Boqueirão, 2450 - Igara, Canoas - RS, 92032-420");
        CentroDeDistribuicao centro1 = new CentroDeDistribuicao(null, "Centro de Distribuição Prosperidade",
                "Av. Borges de Medeiros 1501 Porto Alegre CEP 90119900");
        CentroDeDistribuicao centro2 = new CentroDeDistribuicao(null, "Centro de Distribuição Reconstrução",
                "R. Dr. Décio Martins Costa, 312 - Vila Eunice Nova, Cachoeirinha - RS, 94920-170");
        service.save(centro);
        service.save(centro1);
        service.save(centro2);*/
        loop:
        while (true) {
            System.out.println("1 - Gerenciar Centros de Distribuição");
            System.out.println("2 - Gerenciar Produtos");
            System.out.println("3 - Gerenciar Estoque");
            System.out.println("4 - Sair");
            System.out.print("Digite a opção desejada: ");
            int opt = LeitorDeDados.lerIntInterval("Opção inválida! Digite novamente: ", 1, 4);
            switch (opt) {
                case 1:
                    CentroControle centroControle = new CentroControle(new CentroService());
                    centroControle.menu();
                    break;
                case 2:
                    ProdutoMenu produtoMenu = new ProdutoMenu(new ProdutoController(new ProdutoService()));
                    produtoMenu.menu();
                    break;
                case 3:
                    EstoqueMenu estoqueMenu = new EstoqueMenu(new EstoqueController(new EstoqueService(), new CentroService(), new ProdutoService()));
                    estoqueMenu.menu();
                    break;
                default:
                    LeitorDeDados.close();
                    JpaUtil.close();
                    break loop;
            }
        }
    }
}
