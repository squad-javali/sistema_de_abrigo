package com.compass.application.controllers.centrodedistribuicao;

import com.compass.services.CentroService;
import com.compass.utils.LeitorDeDados;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CentroControle {

    private CentroService service;

    public void menu() {
        while (true) {
            System.out.println("Centro de Distribuição");
            System.out.println("1 - Listar Centro de Distribuição");
            System.out.println("2 - Voltar");
            System.out.print("Digite a opção desejada: ");
            int opt = LeitorDeDados.lerIntInterval("Opção Invalida: ", 1, 2);
            if (opt == 1) {
                LeitorDeDados.imprimirMap(service.findAll());
            } else break;
        }
    }
}
