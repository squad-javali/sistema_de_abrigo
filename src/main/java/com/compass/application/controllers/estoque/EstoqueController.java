package com.compass.application.controllers.estoque;

import com.compass.domain.CentroDeDistribuicao;
import com.compass.domain.Estoque;
import com.compass.domain.Produto;
import com.compass.domain.exceptions.NoItemsRegisteredException;
import com.compass.services.CentroService;
import com.compass.services.EstoqueService;
import com.compass.services.ProdutoService;
import com.compass.utils.LeitorDeDados;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.compass.utils.LeitorDeDados.*;

@AllArgsConstructor
public class EstoqueController {

    private EstoqueService estoqueService;
    private CentroService centroService;
    private ProdutoService produtoService;

    public void adicionar() {
        try {
            Produto produto = LeitorDeDados.selecionarItem("Selecione um produto:", "produto", produtoService.findAll());

            CentroDeDistribuicao centro = LeitorDeDados.selecionarItem("Selecione um Centro:", "centro de distribuição", centroService.findAll());

            System.out.println("Digite a quantidade de itens:");
            int quantidade = LeitorDeDados.lerIntInterval("Valor Invalido:", 1, 1000);

            Estoque estoque = new Estoque(null, produto, centro, quantidade, 1000);
            estoqueService.save(estoque);
        } catch (NoItemsRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }

    public void remover() {
        try {
            Estoque estoque = LeitorDeDados.selecionarItem("Selecione um estoque:", "estoque", estoqueService.findAll());
            estoqueService.deleteById(estoque.getId());
        } catch (NoItemsRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {
        Map<Integer, Estoque> estoques = estoqueService.findAll();
        LeitorDeDados.imprimirMap(estoques);
    }

    public void atualizar() {
        try {
            Estoque estoque = selecionarItem("Selecione um estoque:", "estoque", estoqueService.findAll());
            System.out.println("Digite a quantidade de itens:");
            int quantidade = LeitorDeDados.lerIntInterval("Valor Invalido:", 1, 1000);
            estoque.setQuantidade(quantidade);
            estoqueService.update(estoque);
        } catch (NoItemsRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }
}
