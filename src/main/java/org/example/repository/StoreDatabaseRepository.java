package org.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.IncorrectSQLInputException;
import org.example.exception.NoSuchEntityException;
import org.example.modal.Store;

import java.sql.*;
import java.util.Optional;

@Slf4j
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
            log.info("Success connection");
            preparedStatement.setString(1, store.getNameOfStore());
            preparedStatement.setString(2, store.getTown());
            preparedStatement.execute();
            log.info("Adding of store to database successfully");
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Impossible connect with database", e);
        }
    }

    /**
     * @param id which is needed to delete
     * @return boolean type to understand whether execute or not.
     */
    public int remove(int id) {
        try (final var statement = openConnection().prepareStatement(DELETE)) {
            log.info("Success connection");
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Impossible connect with database", e);
        }
    }

    /**
     * @param id which is needed to get
     * @return object store.
     */

    public Optional<Store> loadById(int id) {
        try (final var statement = openConnection().prepareStatement(SELECT)) {
            log.info("Success connection");
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                log.info("Getting store object, if we get empty object, we will get NoSuchEntityException");
                return Optional.of(new Store(resultSet.getInt("id"), resultSet.getString("name store"), resultSet.getString("town")));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Impossible connect with database", e);
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
