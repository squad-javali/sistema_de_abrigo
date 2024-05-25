package com.compass.domain.exceptions;

import java.io.Serial;

public class DBException extends RuntimeException{
    @Serial
    private final long serialVersionUID = 1L;

    public DBException(String message) {
        super(message);
    }
}
