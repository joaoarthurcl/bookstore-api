package com.jlee.bookstore.exceptions;

public class DataIntegrityViolationException extends RuntimeException {
    private static final long serialVersionUID = 2441125160175748169L;

    public DataIntegrityViolationException(String message) {
        super(message);
    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
