package com.jlee.bookstore.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 3248606591438914783L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
