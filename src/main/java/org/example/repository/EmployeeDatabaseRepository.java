package org.example.repository;

import org.example.exception.IncorrectSQLInputException;
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
    private static final String SELECT = "SELECT * FROM employee JOIN store ON employee.store = store.id  WHERE employee.id = ?";
    private static final String SELECT_ALL_EMPLOYEE = "SELECT * FROM employee JOIN store ON employee.store = store.id";

    /**
     * @param employee which is needed to insert to DB
     */
    public void add(Employee employee) {
        try (var preparedStatement = openConnection().prepareStatement(INSERT)) {
            preparedStatement.setInt(1, employee.getStore().getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setInt(5, employee.getSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Incorrect date", e);
        }
    }

    /**
     * @param id which is needed to find
     * @return found object.
     */
    public Employee load(int id) {
        try (var statement = openConnection().prepareStatement(SELECT)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return loadEmployee(resultSet);
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Incorrect date", e);
        }
    }

    /**
     * @param id which is needed to delete
     * @return either success or not.
     */
    public boolean remove(int id) {
        try (var statement = openConnection().prepareStatement(DELETE_FROM_DB)) {
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Incorrect date", e);
        }
    }

    /**
     * @return list with all salaries;
     */
    public ArrayList<Employee> loadAllEmployees() {
        var employees = new ArrayList<Employee>();
        try (Statement statement = openConnection().createStatement()) {
            var resultSet = statement.executeQuery(SELECT_ALL_EMPLOYEE);
            while (resultSet.next()) {
                employees.add(loadEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Incorrect date", e);
        }
        return employees;
    }

    private Employee loadEmployee(ResultSet resultSet) {
        try {
            return new Employee(resultSet.getInt("id"), resultSet.getString("first name"),
                    resultSet.getString("last name"), resultSet.getString("position"),
                    resultSet.getInt("salary"),
                    new Store(resultSet.getInt("id"), resultSet.getString("name store"), resultSet.getString("town")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
