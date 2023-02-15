package org.example;

import java.lang.annotation.Repeatable;
import java.sql.*;

public class DatabaseRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "HaZhlkZEd1wolFs";
    private static Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;

    {
        try {
            connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
            statement = connection.createStatement();
           // preparedStatement = connection.prepareStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insert() {
      //  preparedStatement.execute();
    }

    private void select() {
        try (Statement statement = connection.createStatement();){
       //     statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void update() {
        try (Statement statement = connection.createStatement()){
      //      statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // drop
    private void delete() {
        try (Statement statement = connection.createStatement()){
     //       statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
