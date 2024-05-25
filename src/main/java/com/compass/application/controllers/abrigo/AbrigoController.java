package com.compass.application.controllers;

import java.util.Scanner;
import java.sql.SQLException;
import java.util.List;

import com.compass.application.entities.Abrigo;
import com.compass.application.services.AbrigoService;
import com.compass.application.validations.AbrigoValidation;

public class AbrigoController {

    private final AbrigoService abrigoService;
    private final Scanner scanner;

    public AbrigoController(AbrigoService abrigoService) {
        this.abrigoService = abrigoService;
        this.scanner = new Scanner(System.in);
    }

    public void cadastrarAbrigo() throws SQLException {
        Abrigo abrigo = new Abrigo();
        System.out.println("Nome: ");
        abrigo.setName(scanner.nextLine());
        System.out.println("Endereço: ");
        abrigo.setAddress(scanner.nextLine());
        System.out.println("Responsável: ");
        abrigo.setResponsible(scanner.nextLine());
        System.out.println("Telefone: ");
        abrigo.setPhone(scanner.nextLine());
        System.out.println("Email: ");
        abrigo.setEmail(scanner.nextLine());
        System.out.println("Capacidade: ");
        abrigo.setCapacity(scanner.nextInt());
        System.out.println("Ocupação (%): ");
        abrigo.setOccupancy(scanner.nextDouble());
        scanner.nextLine();

        if (AbrigoValidation.validar(abrigo)) {
            abrigoService.cadastrarAbrigo(abrigo);
            System.out.println("Abrigo cadastrado com sucesso!");
        } else {
            System.out.println("Dados inválidos");
        }
    }

    public void listarTodosAbrigos() {
        List<Abrigo> abrigos = abrigoService.listarTodosAbrigos();
        for (Abrigo abrigo : abrigos) {
            System.out.println(abrigo);
        }
    }

    public void atualizarAbrigo() throws SQLException {
        System.out.println("ID do Abrigo a ser editado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha restante
        Abrigo abrigo = abrigoService.buscarAbrigoPorId(id);
        if (abrigo == null) {
            System.out.println("Abrigo não encontrado.");
            return;
        }

        System.out.println("Nome (" + abrigo.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) abrigo.setName(name);

        System.out.println("Endereço (" + abrigo.getAddress() + "): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) abrigo.setAddress(address);

        System.out.println("Responsável (" + abrigo.getResponsible() + "): ");
        String responsible = scanner.nextLine();
        if (!responsible.isEmpty()) abrigo.setResponsible(responsible);

        System.out.println("Telefone (" + abrigo.getPhone() + "): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) abrigo.setPhone(phone);

        System.out.println("Email (" + abrigo.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) abrigo.setEmail(email);

        System.out.println("Capacidade (" + abrigo.getCapacity() + "): ");
        String capacityStr = scanner.nextLine();
        if (!capacityStr.isEmpty()) abrigo.setCapacity(Integer.parseInt(capacityStr));

        System.out.println("Ocupação (%) (" + abrigo.getOccupancy() + "): ");
        String occupancyStr = scanner.nextLine();
        if (!occupancyStr.isEmpty()) abrigo.setOccupancy(Double.parseDouble(occupancyStr));

        if (AbrigoValidation.validar(abrigo)) {
            abrigoService.atualizarAbrigo(abrigo);
            System.out.println("Abrigo atualizado com sucesso!");
        } else {
            System.out.println("Dados inválidos. Edição não realizada.");
        }
    }

    public void deletarAbrigo() throws SQLException {
        System.out.println("ID do Abrigo a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        if (abrigoService.deletarAbrigo(id)) {
            System.out.println("Abrigo deletado com sucesso!");
        } else {
            System.out.println("Abrigo não encontrado.");
        }
    }
}


