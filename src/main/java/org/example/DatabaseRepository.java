package org.example;

import java.lang.annotation.Repeatable;
import java.sql.*;

public class DatabaseRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "HaZhlkZEd1wolFs";
    private static Connection connection;
    private static final String INSERT_INTO_EMPLOYEE_TABLE = "INSERT INTO employee VALUES (?,?,?,?,?,?)";
    private static final String INSERT_INTO_STORE_TABLE = "INSERT INTO store VALUES (?,?)";
    private static final String GET_FINAL_INDEX = "SELECT id FROM employee ORDER BY id DESC limit 1";


    {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(String[] things) {
        try {
            int lastId = getLastId();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_EMPLOYEE_TABLE);
            int salary = Integer.parseInt(things[3]);
            //check whether exit or not
            int store = Integer.parseInt(things[4]);
            preparedStatement.setInt(1, lastId + 1);
            preparedStatement.setInt(2, store);
            preparedStatement.setString(3, things[0]);
            preparedStatement.setString(4, things[1]);
            preparedStatement.setString(5, things[2]);
            preparedStatement.setInt(6, salary);
            preparedStatement.execute();

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void select() {
        try (Statement statement = connection.createStatement();) {
            //     statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        try (Statement statement = connection.createStatement()) {
            //      statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // drop
    public void delete() {
        try (Statement statement = connection.createStatement()) {
            //       statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkStore() {

    }

    private int getLastId() {
        PreparedStatement getID = null;
        int id = 0;
        try {
            getID = connection.prepareStatement(GET_FINAL_INDEX);
            ResultSet resultSet = getID.executeQuery();
            getID.close();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
