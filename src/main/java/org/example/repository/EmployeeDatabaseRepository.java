package org.example.repository;

import org.example.exception.NoSuchException;
import org.example.modal.Employee;
import org.example.modal.Store;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDatabaseRepository {
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

    /**
     * @param employee which is needed to insert to DB
     */
    public void insert(Employee employee) {
        try {
            String sql = "INSERT INTO employee(store, `first name`, `last name`, position, salary) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employee.getStore().getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setInt(5, employee.getSalary());
            preparedStatement.execute();
            preparedStatement.close();
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
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee JOIN store ON employee.store = store.id  WHERE employee.id = " + id);
            while (resultSet.next()) {
                employee = new Employee(resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getInt(6),
                        store = new Store(resultSet.getString(7), resultSet.getString(8)));
                store.setId(resultSet.getInt(2));
                employee.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    /**
     * @param id which is needed to delete
     */
    public void delete(int id) {
        try (Statement statement = connection.createStatement()) {
            if (!statement.execute("DELETE FROM employee WHERE employee.id = " + id)) {
                throw new NoSuchException("There isn't such a employee");
            }
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
            ResultSet resultSet = statement.executeQuery("SELECT salary FROM employee");
            while (resultSet.next()) {
                salaries.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salaries;
    }
}
