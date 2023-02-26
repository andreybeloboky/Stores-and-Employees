package org.example.repository;

import org.example.exception.IncorrectSQLInputException;
import org.example.exception.NoSuchEntityException;
import org.example.modal.Store;

import java.sql.*;
import java.util.Optional;

public class StoreDatabaseRepository {
    private static final String INSERT = "INSERT INTO store(`name store`, `town`) VALUES (?,?)";
    private static final String DELETE = "DELETE FROM store WHERE store.id = ?";
    private static final String SELECT = "SELECT * FROM store WHERE store.id = ?";
    private static final String LOGIN = System.getenv("mySQLLoginShop");
    private static final String PASSWORD = System.getenv("mySQLPasswordShop");
    private static final String URL = System.getenv("URLTaskShop");

    /**
     * @param store which is needed to insert to db.
     */
    public void add(Store store) {
        try (final var preparedStatement = openConnection().prepareStatement(INSERT)) {
            preparedStatement.setString(1, store.getNameOfStore());
            preparedStatement.setString(2, store.getTown());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("There isn't store in list of database", e);
        }
    }

    /**
     * @param id which is needed to delete
     * @return boolean type to understand whether execute or not.
     */
    public boolean remove(int id) {
        try (final var statement = openConnection().prepareStatement(DELETE)) {
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("There isn't store in list of database", e);
        }
    }

    /**
     * @param id which is needed to get
     * @return object store.
     */
    public Store getById(int id) {
        try (final var statement = openConnection().prepareStatement(SELECT)) {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            var store = Optional.of(new Store(resultSet.getInt("id"), resultSet.getString("name store"), resultSet.getString("town")));
            return store.orElseThrow(() -> new NoSuchEntityException("There isn't such store id: " + id));
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("There isn't such an store", e);
        }
    }

    private Connection openConnection() {
        try {
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Impossible connect with database", e);
        }
    }
}
