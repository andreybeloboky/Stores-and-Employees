package org.example.service;

import org.example.Store;
import org.example.exception.IncorrectException;
import org.example.repository.DatabaseRepository;
import org.example.service.EmployeeService;
import org.example.validator.StoreNameValidator;

import java.sql.*;
import java.util.ArrayList;

public class StoreService {

    private final DatabaseRepository databaseRepository = new DatabaseRepository();

    private static final EmployeeService employeeService = new EmployeeService();

    private final StoreNameValidator storeNameValidator = new StoreNameValidator();

    /**
     * @param storeOfName is sent by user.
     * @param town is sent by user.
     * @return positive message.
     */
    public String addStore(String storeOfName, String town) {
        if (storeNameValidator.validate(storeOfName)) {
            try (Connection connection = databaseRepository.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO store VALUES (?,?,?)");
                preparedStatement.setInt(1, getLastId() + 1);
                preparedStatement.setString(2, storeOfName);
                preparedStatement.setString(3, town);
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "Successfully";
    }


    /**
     * @param id which is needed to delete
     * @return positive message.
     */
    public String deleteStore(int id) {
        try (Connection connection = databaseRepository.getConnection();
             Statement statement = connection.createStatement()) {
            int lastId = getLastId();
            int idFromEmployee = employeeService.getLastId();
            if (id < lastId && idFromEmployee > 0) {
                statement.execute("DELETE FROM store WHERE employee.id = " + id);
            } else {
                throw new IncorrectException("There isn't such a store");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Successfully";
    }

    /**
     * @param id which is needed to get info from db.
     * @return store object.
     */
    public Store getInfoFromIdStore(int id) {
        Store store = null;
        try (Connection connection = databaseRepository.getConnection();
             Statement statement = connection.createStatement()) {
            checkStoreInDb(id);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM store WHERE store.id = " + id);
            while (resultSet.next()) {
                store = new Store(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return store;
    }

    /**
     * @param store number from db.
     * @return store object.
     */
    public Store generateStoreObject(int store) {
        checkStoreInDb(store);
        Store storeFromDB = null;
        try (Connection connection = databaseRepository.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM store");
            while (resultSet.next()) {
                storeFromDB = new Store(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(2));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return storeFromDB;
    }

    /**
     * @param store object.
     */
    private void checkStoreInDb(int store) {
        PreparedStatement getStore = null;
        ArrayList<Integer> stores = new ArrayList<>();
        try (Connection connection = databaseRepository.getConnection()) {
            getStore = connection.prepareStatement("SELECT store FROM employee GROUP BY store");
            ResultSet resultSet = getStore.executeQuery();
            while (resultSet.next()) {
                stores.add(resultSet.getInt("store"));
            }
            getStore.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (stores.size() < store) {
            throw new IncorrectException("This store doesn't exit");
        }
    }

    /**
     * @return int last id.
     */
    private int getLastId() {
        Statement getID = null;
        int id = 0;
        try (Connection connection = databaseRepository.getConnection();) {
            getID = connection.createStatement();
            ResultSet resultSet = getID.executeQuery("SELECT id FROM store ORDER BY id DESC limit 1");
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            resultSet.close();
            getID.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
