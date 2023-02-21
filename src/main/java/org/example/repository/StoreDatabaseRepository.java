package org.example.repository;

import org.example.exception.NoSuchException;
import org.example.modal.Store;

import java.sql.*;

public class StoreDatabaseRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "HaZhlkZEd1wolFs";
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Store store) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO store(`name store`, `town`) VALUES (?,?)")) {
            preparedStatement.setString(1, store.getNameOfStore());
            preparedStatement.setString(2, store.getTown());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (Statement statement = connection.createStatement()) {
            if (statement.execute("DELETE FROM store WHERE store.id = " + id)) {
                throw new NoSuchException("There isn't such a store");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Store select(int id) {
        Store store = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM store WHERE store.id = " + id);
            while (resultSet.next()) {
                store = new Store(resultSet.getString(2), resultSet.getString(3));
                store.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return store;
    }
}
