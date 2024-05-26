package com.compass.application.controllers.abrigo;

import com.compass.domain.Abrigo;
import com.compass.domain.exceptions.DbIntegrityException;
import com.compass.services.AbrigoService;
import com.compass.utils.LeitorDeDados;
import com.compass.validations.AbrigoValidation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbrigoController {

    private final AbrigoService abrigoService;

    public void cadastrarAbrigo() {
        String nome = LeitorDeDados.lerString("Nome");
        String endereco = LeitorDeDados.lerString("Endereço");
        String responsavel = LeitorDeDados.lerString("Responsável");
        String telefone = LeitorDeDados.lerString("Telefone");
        String email = LeitorDeDados.lerString("Email");
        System.out.println("Digite a capacidade:");
        int capacidade = LeitorDeDados.lerInt("Valor inválido");
        System.out.println("Digite a ocupação:");
        Double ocupacao = LeitorDeDados.lerDouble("Valor inválido");

        Abrigo abrigo = new Abrigo(null,nome, endereco, responsavel,telefone, email, capacidade, ocupacao);
        if (AbrigoValidation.validar(abrigo)) {
            abrigoService.save(abrigo);
            System.out.println("Abrigo cadastrado com sucesso!");
        } else {
            System.out.println("Dados inválidos");
        }
    }

    public void listarTodosAbrigos() {
        LeitorDeDados.imprimirMap(abrigoService.findAll());
    }

    public void atualizarAbrigo() {
        Abrigo abrigo = LeitorDeDados.selecionarItem("Selecione um Abrigo:","abrigo",abrigoService.findAll());

        System.out.println("Nome (" + abrigo.getNome() + "): ");
        String name = LeitorDeDados.lerString("Digite um novo nome");
        if (!name.isEmpty()) abrigo.setNome(name);

        System.out.println("Endereço (" + abrigo.getEndereco() + "): ");
        String address = LeitorDeDados.lerString("Digite um novo Endereço");
        if (!address.isEmpty()) abrigo.setEndereco(address);

        System.out.println("Responsável (" + abrigo.getResponsavel() + "): ");
        String responsible = LeitorDeDados.lerString("Digite um novo Responsável");
        if (!responsible.isEmpty()) abrigo.setResponsavel(responsible);

        System.out.println("Telefone (" + abrigo.getTelefone() + "): ");
        String phone = LeitorDeDados.lerString("Digite um novo phone");
        if (!phone.isEmpty()) abrigo.setTelefone(phone);

        System.out.println("Email (" + abrigo.getEmail() + "): ");
        String email = LeitorDeDados.lerString("Digite um novo email");
        if (!email.isEmpty()) abrigo.setEmail(email);

        System.out.println("Capacidade (" + abrigo.getCapacidade() + "): ");
        String capacityStr = LeitorDeDados.lerString("Digite uma nova capacidade");
        if (!capacityStr.isEmpty()) abrigo.setCapacidade(Integer.parseInt(capacityStr));

        System.out.println("Ocupação (%) (" + abrigo.getOcupacao() + "): ");
        String occupancyStr = LeitorDeDados.lerString("Digite um valor de ocupação");
        if (!occupancyStr.isEmpty()) abrigo.setOcupacao(Double.parseDouble(occupancyStr));

        if (AbrigoValidation.validar(abrigo)) {
            abrigoService.save(abrigo);
            System.out.println("Abrigo atualizado com sucesso!");
        } else {
            System.out.println("Dados inválidos. Edição não realizada.");
        }
    }

    public void deletarAbrigo() {
        try {
            System.out.println("ID do Abrigo a ser deletado: ");
            Abrigo abrigo = LeitorDeDados.selecionarItem("Selecione um abrigo","abrigo",abrigoService.findAll());
            abrigoService.deleteById(abrigo.getId());
            System.out.println("Abrigo deletado com sucesso!");
        } catch (DbIntegrityException e) {
            System.out.println(e.getMessage());
        }
    }
}


