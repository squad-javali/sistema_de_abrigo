package com.compass.application;

import com.compass.domain.ItemListaNecessidades;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    ItemListaNecessidades item = new ItemListaNecessidades();
    item.setQuantidade(100);
    item.setIdProduto(1);
    item.setIdLista(2);
    }
}