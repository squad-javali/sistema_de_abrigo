package com.compass.domain.exceptions;

public class NoItemsRegisteredException extends RuntimeException {
    public NoItemsRegisteredException(String itemType) {
        super("Não há " + itemType + " registrados");
    }
}