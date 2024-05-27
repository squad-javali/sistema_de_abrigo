package com.compass.domain.enums;

import lombok.Getter;

@Getter
public enum RoupaTamanho {
    Infantil(1),
    PP(2),
    P(3),
    M(4),
    G(5),
    GG(6),
    NULL(7);

    private final int code;

    RoupaTamanho(int code) {
        this.code = code;
    }

    public static RoupaTamanho valueOf(Integer code) {
        for (RoupaTamanho status : RoupaTamanho.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }

    public static RoupaTamanho fromString(String size) {
        return switch (size.toUpperCase()) {
            case "INFANTIL" -> Infantil;
            case "PP" -> PP;
            case "P" -> P;
            case "G" -> G;
            case "GG" -> GG;
            case "NULL" -> NULL;
            default -> throw new IllegalArgumentException("Invalid size");
        };
    }
}