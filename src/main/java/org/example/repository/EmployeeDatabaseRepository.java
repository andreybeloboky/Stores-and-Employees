package org.example.repository;

import org.example.modal.Employee;
import org.example.modal.Store;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDatabaseRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "HaZhlkZEd1wolFs";
    private static final String DELETE_FROM_DB = "DELETE FROM employee WHERE employee.id = ?";
    private static final String INSERT = "INSERT INTO employee(store, `first name`, `last name`, position, salary) VALUES (?,?,?,?,?)";
    private static final String SELECT_SALARY = "SELECT salary FROM employee";
    private static final String SELECT = "SELECT * FROM employee JOIN store ON employee.store = store.id  WHERE employee.id = ?";
    private final Connection connection;

    {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param employee which is needed to insert to DB
     */
    public void insert(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setInt(1, employee.getStore().getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setInt(5, employee.getSalary());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id which is needed to find
     * @return found object.
     */
    public Employee select(int id) {
        Employee employee = null;
        Store store;
        try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getInt(6),
                        store = new Store(resultSet.getString(7), resultSet.getString(8)));
                store.setId(resultSet.getInt(2));
                employee.setId(resultSet.getInt(1));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    /**
     * @param id which is needed to delete
     * @return either success or not.
     */
    public boolean delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_FROM_DB)) {
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return list with all salaries;
     */
    public ArrayList<Integer> salary() {
        ArrayList<Integer> salaries = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_SALARY);
            while (resultSet.next()) {
                salaries.add(resultSet.getInt(1));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salaries;
    }
}
