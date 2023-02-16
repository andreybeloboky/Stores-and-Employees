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

    private static final String INSERT_INTO_EMPLOYEE_TABLE = "INSERT INTO employee VALUES (?,?,?,?)";
    private static final String INSERT_INTO_STORE_TABLE = "INSERT INTO store VALUES (?,?)";


    {
        try {
            connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(String [] things) {
        try {
            preparedStatement = connection.prepareStatement(INSERT_INTO_EMPLOYEE_TABLE);
            int number = Integer.getInteger(things[3]);
            int salary = Integer.getInteger(things[3]);
            preparedStatement.setInt(2,number);
            preparedStatement.setString(3, things[0]);
            preparedStatement.setString(4, things[1]);
            preparedStatement.setString(5, things[2]);
         //   preparedStatement.setInt(6, );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ;
    }

    public void select() {
        try (Statement statement = connection.createStatement();){
       //     statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        try (Statement statement = connection.createStatement()){
      //      statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // drop
    public void delete() {
        try (Statement statement = connection.createStatement()){
     //       statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
