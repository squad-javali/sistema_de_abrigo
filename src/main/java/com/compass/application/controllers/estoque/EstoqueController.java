package com.compass.application.controllers.estoque;

import com.compass.domain.CentroDeDistribuicao;
import com.compass.domain.Estoque;
import com.compass.domain.Produto;
import com.compass.domain.exceptions.DbIntegrityException;
import com.compass.domain.exceptions.EntityExistsException;
import com.compass.domain.exceptions.NoItemsRegisteredException;
import com.compass.services.EstoqueService;
import com.compass.services.ProdutoService;
import com.compass.utils.LeitorDeDados;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.compass.utils.LeitorDeDados.selecionarItem;

@AllArgsConstructor
public class EstoqueController {

    private EstoqueService estoqueService;
    private ProdutoService produtoService;

    public void adicionar(CentroDeDistribuicao centro) {
        try {

            Produto produto = LeitorDeDados.selecionarItem("Selecione um produto:", "produto", produtoService.findAll());

            System.out.print("Digite a quantidade de itens:");
            int quantidade = LeitorDeDados.lerIntInterval("Valor Invalido:", 1, 1000);

            Estoque estoque = new Estoque(null, produto, centro, quantidade, 1000);
            estoqueService.save(estoque);
        } catch (NoItemsRegisteredException | EntityExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionarProduto(Integer id) {
        try {
            Estoque estoque = LeitorDeDados.selecionarItem("Selecione um estoque:", "estoque", estoqueService.findAllByCentroId(id));
            System.out.print("Digite a quantidade de itens:");
            int quantidade = LeitorDeDados.lerIntInterval("Valor Invalido:", 1, 1000);
            if (estoque.getQuantidade() + quantidade > 1000) {
                int excedente = (estoque.getQuantidade() + quantidade) - 1000;
                System.out.println("O valor excedeu o limite");
                System.out.println((excedente - quantidade) + " produtos foram adicionados, sobraram:" + excedente);
            }
        } catch (NoItemsRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
            Estoque estoque = LeitorDeDados.selecionarItem("Selecione um estoque:", "estoque", estoqueService.findAllByCentroId(id));
            estoqueService.deleteById(estoque.getId());
        } catch (NoItemsRegisteredException e) {
            System.out.println(e.getMessage());
        } catch (DbIntegrityException e) {
            System.out.println("O produto está cadastrado em um estoque");
        }
    }

    public void removerItens(Integer id) {
        try {
            Estoque estoque = LeitorDeDados.selecionarItem("Selecione um estoque:", "estoque", estoqueService.findAllByCentroId(id));
            System.out.print("Digite a quantidade de itens:");
            int quantidadeSolicitada = LeitorDeDados.lerIntInterval("Valor Inválido:", 1, 1000);

            int quantidadeDisponivel = estoque.getQuantidade();

            if (quantidadeDisponivel < quantidadeSolicitada) {
                System.out.println("Quantidade removida: " + quantidadeDisponivel);
                System.out.println("Faltam: " + (quantidadeSolicitada - quantidadeDisponivel) + " itens para completar o pedido.");
                estoque.setQuantidade(0);
            } else {
                System.out.println("Quantidade removida: " + quantidadeSolicitada);
                estoque.setQuantidade(quantidadeDisponivel - quantidadeSolicitada);
            }

            System.out.println("Valor de estoque: " + estoque.getQuantidade());
            estoqueService.save(estoque);
        } catch (NoItemsRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar(Integer id) {
        Map<Integer, Estoque> estoques = estoqueService.findAllByCentroId(id);
        LeitorDeDados.imprimirMap(estoques);
    }

    public void atualizar(Integer Id) {
        try {
            Estoque estoque = selecionarItem("Selecione um estoque:", "estoque", estoqueService.findAllByCentroId(Id));
            Produto produto = LeitorDeDados.selecionarItem("Selecione um produto:", "produto", produtoService.findAll());
            System.out.print("Digite a quantidade de itens:");
            int quantidade = LeitorDeDados.lerIntInterval("Valor Invalido:", 1, 1000);
            estoque.setQuantidade(quantidade);
            estoque.setProduto(produto);
            estoqueService.save(estoque);
        } catch (NoItemsRegisteredException | EntityExistsException e) {
            System.out.println(e.getMessage());
        }
    }

}