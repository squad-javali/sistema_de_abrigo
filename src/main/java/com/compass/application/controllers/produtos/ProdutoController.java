
package com.compass.application.controllers.produtos;

import com.compass.domain.Produto;
import com.compass.domain.enums.RoupaSexo;
import com.compass.domain.enums.RoupaTamanho;
import com.compass.services.ProdutoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static com.compass.utils.LeitorDeDados.*;

@AllArgsConstructor
@RequiredArgsConstructor
public class ProdutoController {

    private ProdutoService produtoService;

    public void adicionar() {
        Produto produto;
        System.out.println("Selecione uma opção");
        System.out.println("1 - Roupa");
        System.out.println("2 - Alimento");
        System.out.println("3 - Produto de higiene");
        System.out.print("Digite a opção desejada: ");
        int opt = lerIntInterval("Opção inválida! Digite novamente: ", 1, 3);

        System.out.print("Qual o nome do item: ");
        String descricao = lerString("");

        RoupaTamanho tamanho = null;
        RoupaSexo sexo = null;
        if (opt == 1) {
            System.out.println("Digite o tamanho (Infantil/PP/P/M/G/GG):");
            System.out.println("1 - Infantil");
            System.out.println("2 - PP");
            System.out.println("3 - P");
            System.out.println("4 - M");
            System.out.println("5 - G");
            System.out.println("6 - GG");
            tamanho = RoupaTamanho.values()[lerIntInterval("Opção inválida! Digite novamente: ", 1, 6)];

            System.out.println("Digite o sexo (M/F)");
            System.out.println("1 - M");
            System.out.println("2 - F");
            sexo = RoupaSexo.values()[lerIntInterval("Opção inválida! Digite novamente: ", 1, 2)];
            produto = new Produto(null, descricao, tamanho, sexo);
        } else produto = new Produto(null, descricao, RoupaTamanho.NULL, RoupaSexo.N);
        produtoService.save(produto);
    }


    public void listar() {
        Map<Integer,Produto> produtos = produtoService.findAll();
        imprimirMap(produtos);
    }

    public void remover() {
        Produto produto;
        Map<Integer,Produto> produtos = produtoService.findAll();
        imprimirMap(produtos);
        System.out.print("Digite o id do produto: ");
        do {
            int id = lerIntInterval("Id inválido! Digite novamente: ", 1, 1000);
            produto = produtos.get(id);
        } while (produto == null);
        produtoService.deleteById(produto.getId());
    }

    public void atualizar() {
        Produto produto = selecionarItem("Selecione um produto:",  produtoService.findAll());

        System.out.println(produto.getId());
        String descricao = lerString("Digite o nome do item: ");

        RoupaTamanho tamanho = produto.getTamanho();
        RoupaSexo sexo = null;
        if (tamanho != null) {
            System.out.println("Digite o tamanho (Infantil/PP/P/M/G/GG):");
            System.out.println("1 - Infantil");
            System.out.println("2 - PP");
            System.out.println("3 - P");
            System.out.println("4 - M");
            System.out.println("5 - G");
            System.out.println("6 - GG");
            tamanho = RoupaTamanho.values()[lerIntInterval("Opção inválida! Digite novamente: ", 1, 6)];

            System.out.println("Digite o sexo (M/F)");
            System.out.println("1 - M");
            System.out.println("2 - F");
            sexo = RoupaSexo.values()[lerIntInterval("Opção inválida! Digite novamente: ", 1, 2)];
            produto = new Produto(produto.getId(), descricao, tamanho, sexo);
        } else produto = new Produto(produto.getId(), descricao, RoupaTamanho.NULL, RoupaSexo.N);
        produtoService.save(produto);
    }
}
