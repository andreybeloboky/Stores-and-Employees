package org.example.repository;

import org.example.modal.Store;

import java.sql.*;

public class StoreDatabaseRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "HaZhlkZEd1wolFs";
    private static final String INSERT = "INSERT INTO store(`name store`, `town`) VALUES (?,?)";
    private static final String DELETE = "DELETE FROM store WHERE store.id = ?";
    private static final String SELECT = "SELECT * FROM store WHERE store.id = ?";
    private final Connection connection;

    {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param store which is needed to insert to db.
     */
    public void insert(Store store) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, store.getNameOfStore());
            preparedStatement.setString(2, store.getTown());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id which is needed to delete
     * @return boolean type to understand whether execute or not.
     */
    public boolean delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id which is needed to get
     * @return object store.
     */
    public Store select(int id) {
        Store store = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
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
