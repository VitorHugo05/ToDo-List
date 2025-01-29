package com.vitordev.todolist.services.exception;

import java.io.Serial;
import java.io.Serializable;

public class ObjectNotFound extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public ObjectNotFound(String message) {
        super(message);
    }
}
