package com.compass.domain.exceptions;

import java.io.Serial;

public class NoItemsRegisteredException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NoItemsRegisteredException(String itemType) {
        super("Não há " + itemType + " registrados");
    }
}