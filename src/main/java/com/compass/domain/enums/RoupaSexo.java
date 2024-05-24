package com.compass.domain.enums;

import lombok.Getter;

@Getter
public enum RoupaSexo {
    M(0),
    F(1),
    N(2);

    private final int code;

    RoupaSexo(int code) {
        this.code = code;
    }

    public static RoupaSexo valueOf(Integer code) {
        for (RoupaSexo status : RoupaSexo.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }

    public static RoupaSexo fromString(String size) {
        return switch (size.toUpperCase()) {
            case "M" -> M;
            case "F" -> F;
            case "N" -> N;
            default -> throw new IllegalArgumentException("Invalid size");
        };
    }
}