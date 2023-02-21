package org.example.exception;

public class IncorrectStoreNameException extends RuntimeException{
    public IncorrectStoreNameException(String message) {
        super(message);
    }
}
