package com.compass.application.controllers.produtos;

import com.compass.domain.exceptions.DbIntegrityException;
import com.compass.domain.Produto;
import com.compass.domain.enums.RoupaSexo;
import com.compass.domain.enums.RoupaTamanho;
import com.compass.domain.exceptions.EntityExistsException;
import com.compass.domain.exceptions.NoItemsRegisteredException;
import com.compass.services.ProdutoService;
import com.compass.utils.LeitorDeDados;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    public void adicionar() {
        System.out.println("Selecione uma opção:");
        ProdutoTipo tipo = selecionarTipoDeProduto();

        String descricao = LeitorDeDados.lerString("Qual o nome do item");

        Produto produto;
        if (tipo == ProdutoTipo.ROUPA) {
            RoupaTamanho tamanho = selecionarTamanhoRoupa();
            RoupaSexo sexo = selecionarSexoRoupa();
            produto = new Produto(null, descricao, tamanho, sexo);
        } else {
            produto = new Produto(null, descricao, RoupaTamanho.NULL, RoupaSexo.N);
        }

        try {
            produtoService.save(produto);
        } catch (EntityExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {
        Map<Integer, Produto> produtos = produtoService.findAll();
        LeitorDeDados.imprimirMap(produtos);
    }

    public void remover() {
        try {
            Produto produto = LeitorDeDados.selecionarItem("Selecione um produto", "produto", produtoService.findAll());
            produtoService.deleteById(produto.getId());
        } catch (NoItemsRegisteredException e) {
            System.out.println(e.getMessage());
        } catch (DbIntegrityException e) {
            System.out.println("O produto está cadastrado em um estoque.");
        }
    }

    public void atualizar() {
        Produto produto = LeitorDeDados.selecionarItem("Selecione um produto:", "produto", produtoService.findAll());

        String descricao = LeitorDeDados.lerString("Digite o nome do item");

        if (produto.getTamanho() != RoupaTamanho.NULL) {
            RoupaTamanho tamanho = selecionarTamanhoRoupa();
            RoupaSexo sexo = selecionarSexoRoupa();
            produto = new Produto(produto.getId(), descricao, tamanho, sexo);
        } else {
            produto = new Produto(produto.getId(), descricao, RoupaTamanho.NULL, RoupaSexo.N);
        }
        produtoService.save(produto);
    }

    private ProdutoTipo selecionarTipoDeProduto() {
        System.out.println("1 - Roupa");
        System.out.println("2 - Alimento");
        System.out.println("3 - Produto de higiene");
        int opt = LeitorDeDados.lerIntInterval("Opção inválida! Digite novamente: ", 1, 3);
        return ProdutoTipo.fromInt(opt);
    }

    private RoupaTamanho selecionarTamanhoRoupa() {
        System.out.println("Digite o tamanho (Infantil/PP/P/M/G/GG):");
        System.out.println("1 - Infantil");
        System.out.println("2 - PP");
        System.out.println("3 - P");
        System.out.println("4 - M");
        System.out.println("5 - G");
        System.out.println("6 - GG");
        int opt = LeitorDeDados.lerIntInterval("Opção inválida! Digite novamente: ", 1, 6);
        return RoupaTamanho.valueOf(opt);
    }

    private RoupaSexo selecionarSexoRoupa() {
        System.out.println("Digite o sexo (M/F):");
        System.out.println("1 - M");
        System.out.println("2 - F");
        int opt = LeitorDeDados.lerIntInterval("Opção inválida! Digite novamente: ", 1, 2);
        return RoupaSexo.valueOf(opt);
    }

    enum ProdutoTipo {
        ROUPA(1),
        ALIMENTO(2),
        HIGIENE(3);

        private final int value;

        ProdutoTipo(int value) {
            this.value = value;
        }

        public static ProdutoTipo fromInt(int value) {
            for (ProdutoTipo tipo : values()) {
                if (tipo.value == value) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Valor inválido: " + value);
        }
    }
}