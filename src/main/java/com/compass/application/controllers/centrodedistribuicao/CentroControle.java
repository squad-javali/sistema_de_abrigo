package com.compass.application.controllers.centrodedistribuicao;

import com.compass.domain.CentroDeDistribuicao;
import com.compass.services.CentroService;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.compass.utils.LeitorDeDados.lerIntInterval;

@AllArgsConstructor
public class CentroControle {

    private CentroService service;

    public void menu() {
        while (true) {
            System.out.println("Centro de Distribuição");
            System.out.println("1 - Listar Centro de Distribuição");
            System.out.println("2 - Voltar");
            int opt = lerIntInterval("Opção Invalida: ", 1, 2);
            if (opt == 1) {
                Map<Integer,CentroDeDistribuicao> centros = service.findAll();
                for (CentroDeDistribuicao centro : centros.values()) {
                    System.out.println(centro);
                }
            } else break;
        }
    }
}
