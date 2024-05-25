package com.compass.application.validations;

import java.util.regex.Pattern;

import com.compass.application.entities.Abrigo;

public class AbrigoValidation {

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
    "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validar(Abrigo abrigo) {
        return validarNome(abrigo.getName()) &&
               validarEndereco(abrigo.getAddress()) &&
               validarResponsavel(abrigo.getResponsible()) &&
               validarTelefone(abrigo.getPhone()) &&
               validarEmail(abrigo.getEmail()) &&
               validarCapacidade(abrigo.getCapacity()) &&
               validarOcupacao(abrigo.getOccupancy());
    }

    private static boolean validarNome(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private static boolean validarEndereco(String addresss) {
        return addresss != null && !addresss.trim().isEmpty();
    }

    private static boolean validarResponsavel(String responsible) {
        return responsible != null && !responsible.trim().isEmpty();
    }

    private static boolean validarTelefone(String phone) {
        return phone != null && phone.matches("\\d{10,11}");
    }

    private static boolean validarEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).find();
    }

    private static boolean validarCapacidade(int capacity) {
        return capacity > 0;
    }

    private static boolean validarOcupacao(double occupancy) {
        return occupancy >= 0 && occupancy <= 100;
    }
}
