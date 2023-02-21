package org.example.exception;

public class NoSuchException extends RuntimeException{
    public NoSuchException(String message) {
        super(message);
    }
}
