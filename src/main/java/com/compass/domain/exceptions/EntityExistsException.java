package com.compass.domain.exceptions;

import java.io.Serial;

public class EntityExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public EntityExistsException(String msg) {
        super(msg);
    }
}