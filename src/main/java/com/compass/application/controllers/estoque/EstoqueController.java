package com.compass.application.controllers.estoque;

import com.compass.domain.CentroDeDistribuicao;
import com.compass.domain.Estoque;
import com.compass.domain.Produto;
import com.compass.services.CentroService;
import com.compass.services.EstoqueService;
import com.compass.services.ProdutoService;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.compass.utils.LeitorDeDados.*;

@AllArgsConstructor
public class EstoqueController {

    private EstoqueService estoqueService;
    private CentroService centroService;
    private ProdutoService produtoService;

    public void adicionar() {
        Produto produto = selecionarItem("Selecione um produto:", produtoService.findAll());

        CentroDeDistribuicao centro = selecionarItem("Selecione um Centro:",  centroService.findAll());

        System.out.println("Digite a quantidade de itens:");
        int quantidade = lerIntInterval("Valor Invalido:",1,1000);

        Estoque estoque = new Estoque(null,produto,centro,quantidade,1000);
        estoqueService.save(estoque);
    }
    public void remover() {
        Estoque estoque = selecionarItem("Selecione um estoque:",estoqueService.findAll());
        estoqueService.deleteById(estoque.getId());
    }

    public void listar() {
        Map<Integer,Estoque> estoques = estoqueService.findAll();
        imprimirMap(estoques);
    }
}
