package org.example.repository;

import org.example.exception.IncorrectSQLInputException;
import org.example.modal.Store;

import java.sql.*;

public class StoreDatabaseRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "HaZhlkZEd1wolFs";
    private static final String INSERT = "INSERT INTO store(`name store`, `town`) VALUES (?,?)";
    private static final String DELETE = "DELETE FROM store WHERE store.id = ?";
    private static final String SELECT = "SELECT * FROM store WHERE store.id = ?";

    /**
     * @param store which is needed to insert to db.
     */
    public void add(Store store) {
        try (var preparedStatement = openConnection().prepareStatement(INSERT)) {
            preparedStatement.setString(1, store.getNameOfStore());
            preparedStatement.setString(2, store.getTown());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Incorrect date", e);
        }
    }

    /**
     * @param id which is needed to delete
     * @return boolean type to understand whether execute or not.
     */
    public boolean remove(int id) {
        try (var statement = openConnection().prepareStatement(DELETE)) {
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Incorrect date", e);
        }
    }

    /**
     * @param id which is needed to get
     * @return object store.
     */
    public Store add(int id) {
        try (var statement = openConnection().prepareStatement(SELECT)) {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            return new Store(resultSet.getInt("id"), resultSet.getString("name store"), resultSet.getString("town"));
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Incorrect date", e);
        }
    }

    private Connection openConnection() {
        try {
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Incorrect date", e);
        }
    }
}
