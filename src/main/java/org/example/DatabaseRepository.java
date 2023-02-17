package org.example;

import java.lang.annotation.Repeatable;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "HaZhlkZEd1wolFs";

    /**
     * @return connection with db.
     */
    public Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
