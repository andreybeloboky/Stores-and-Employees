package org.example.exception;

import java.sql.SQLException;

public class IncorrectSQLInputException extends RuntimeException {
    public IncorrectSQLInputException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
