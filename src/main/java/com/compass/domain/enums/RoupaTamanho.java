package com.compass.domain.enums;

import lombok.Getter;

@Getter
public enum RoupaTamanho {
    Infantil(1),
    PP(2),
    P(3),
    G(4),
    GG(5),
    NULL(6);

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
        switch (size.toUpperCase()) {
            case "INFANTIL":
                return Infantil;
            case "PP":
                return PP;
            case "P":
                return P;
            case "G":
                return G;
            case "GG":
                return GG;
            case "NULL":
                return NULL;
            default:
                throw new IllegalArgumentException("Invalid size");
        }
    }
}
