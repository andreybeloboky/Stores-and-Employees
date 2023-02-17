package org.example.service;

import org.example.*;
import org.example.exception.IncorrectException;
import org.example.repository.DatabaseRepository;
import org.json.JSONObject;

import java.sql.*;

public class EmployeeService {
    private final DatabaseRepository databaseRepository = new DatabaseRepository();
    private final StoreService storeService = new StoreService();

    /**
     * @param id - get id from user.
     * @return string with positive message.
     */
    public String deleteEmployee(int id) {
        try (Connection connection = databaseRepository.getConnection();
             Statement statement = connection.createStatement()) {
            int lastId = getLastId();
            if (id < lastId) {
                statement.execute("DELETE FROM employee WHERE employee.id = " + id);
            } else {
                throw new IncorrectException("There isn't such a employee");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Successfully";
    }

    /**
     * @param s - JSON string.
     * @return string with positive message.
     */
    public String addEmployee(String s) {
        Employee employee = getEmployeeObject(s);
        insert(employee);
        return "Successfully";
    }

    /**
     * @param id which need to get from user.
     * @return employee object
     */
    public Employee getInfoFromIdEmployee(int id) {
        Employee employee = null;
        try (Connection connection = databaseRepository.getConnection();
             Statement statement = connection.createStatement()) {
            int lastId = getLastId();
            if (id < lastId) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM employee JOIN store ON employee.store = store.id  WHERE employee.id = " + id);
                while (resultSet.next()) {
                    employee = new Employee(resultSet.getInt(1), resultSet.getString(3),
                            resultSet.getString(4), checkPosition(resultSet.getString(5)),
                            resultSet.getInt(6),
                            new Store(resultSet.getInt(2), resultSet.getString(7), resultSet.getString(8)));
                }
            } else {
                throw new IncorrectException("There isn't such a employee");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    /**
     * @return last id at DB
     */
    public int getLastId() {
        Statement getID = null;
        int id = 0;
        try (Connection connection = databaseRepository.getConnection();) {
            getID = connection.createStatement();
            ResultSet resultSet = getID.executeQuery("SELECT id FROM employee ORDER BY id DESC limit 1");
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            getID.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    /**
     * @param s JSON string.
     * @return employee object.
     */
    private Employee getEmployeeObject(String s) {
        Employee employee = null;
        int id = getLastId();
        try {
            JSONObject json = new JSONObject(s);
            String firstName = json.getString("firstName");
            String lastName = json.getString("lastName");
            String positionString = json.getString("position");
            Position position = checkPosition(positionString);
            int salary = json.getInt("salary");
            int store = json.getInt("store");
            Store storeObject = storeService.generateStoreObject(store);
            employee = new Employee(id + 1, firstName, lastName, position, salary, storeObject);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return employee;
    }

    /**
     * @param s string position from user, to inspect and convert to position enum.
     * @return position enum.
     */
    private Position checkPosition(String s) {
        int manager = Position.MANAGER.toString().compareToIgnoreCase(s);
        int sa = Position.SA.toString().compareToIgnoreCase(s);
        int director = Position.DIRECTOR.toString().compareToIgnoreCase(s);
        Position position = null;
        if (manager == 0) {
            position = Position.MANAGER;
        } else if (sa == 0) {
            position = Position.SA;
        } else if (director == 0) {
            position = Position.DIRECTOR;
        } else {
            throw new IncorrectException("There isn't such a position");
        }
        return position;
    }

    /**
     * @param employee which got from JSON.
     */
    private void insert(Employee employee) {
        try (Connection connection = databaseRepository.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee VALUES (?,?,?,?,?,?)");
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setInt(2, employee.getStore().getId());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.setString(5, employee.getPosition().toString());
            preparedStatement.setInt(6, employee.getSalary());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
