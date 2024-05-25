package com.compass.application.filter;

import java.util.List;
import java.util.stream.Collectors;

import com.compass.application.entities.Abrigo;
import com.compass.application.services.AbrigoService;

public class AbrigoFilter {

    private AbrigoService abrigoService;

    public AbrigoFilter(AbrigoService abrigoService) {
        this.abrigoService = abrigoService;
    }

    public List<Abrigo> filtrarPorCapacidade(int capacity) {
        return abrigoService.listarTodosAbrigos().stream()
                .filter(abrigo -> abrigo.getCapacity() >= capacity)
                .collect(Collectors.toList());
    }

    public List<Abrigo> filtrarPorOcupacao(double occupancy) {
        return abrigoService.listarTodosAbrigos().stream()
                .filter(abrigo -> abrigo.getOccupancy() <= occupancy)
                .collect(Collectors.toList());
    }

}
