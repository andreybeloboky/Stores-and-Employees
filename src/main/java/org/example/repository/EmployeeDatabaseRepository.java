package org.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.IncorrectSQLInputException;
import org.example.interfaces.EmployeeRepository;
import org.example.modal.Employee;
import org.example.modal.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class EmployeeDatabaseRepository implements EmployeeRepository {
    private static final String DELETE_FROM_DB = "DELETE FROM employee WHERE employee.id = ?";
    private static final String INSERT = "INSERT INTO employee(store, `first name`, `last name`, position, salary) VALUES (?,?,?,?,?)";
    private static final String SELECT = "SELECT * FROM employee JOIN store ON employee.store = store.id  WHERE employee.id = ?";
    private static final String SELECT_ALL_EMPLOYEE = "SELECT * FROM employee JOIN store ON employee.store = store.id";
    private static final String LOGIN = System.getenv("mySQLLoginShop");
    private static final String PASSWORD = System.getenv("mySQLPasswordShop");
    private static final String URL = System.getenv("URLTaskShop");

    /**
     * @param employee which is needed to insert to DB
     */
    public void add(Employee employee) {
        try (final var preparedStatement = openConnection().prepareStatement(INSERT)) {
            preparedStatement.setInt(1, employee.getStore().getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setInt(5, employee.getSalary());
            preparedStatement.execute();
            log.info("Adding of employee to database successfully" + employee);
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Impossible adding", e);
        }
    }

    /**
     * @param id which is needed to find
     * @return found object.
     */
    public Optional<Employee> load(int id) {
        try (final var statement = openConnection().prepareStatement(SELECT)) {
            statement.setInt(1, id);
            final var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(loadEmployee(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Impossible loading", e);
        }
    }

    /**
     * @param id which is needed to delete
     * @return either success or not.
     */
    public boolean remove(int id) {
        try (final var statement = openConnection().prepareStatement(DELETE_FROM_DB)) {
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Impossible deleting", e);
        }
    }

    /**
     * @return list with all salaries;
     */
    public ArrayList<Employee> loadAllEmployees() {
        final var employees = new ArrayList<Employee>();
        try (final var statement = openConnection().createStatement()) {
            var resultSet = statement.executeQuery(SELECT_ALL_EMPLOYEE);
            while (resultSet.next()) {
                employees.add(loadEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Something wrong with loading data from employee", e);
        }
        log.info("Return list of employees");
        return employees;
    }

    private Employee loadEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(resultSet.getInt("id"), resultSet.getString("first name"),
                resultSet.getString("last name"), resultSet.getString("position"),
                resultSet.getInt("salary"),
                new Store(resultSet.getInt("id"), resultSet.getString("name store"), resultSet.getString("town")));
    }

    private Connection openConnection() {
        try {
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new IncorrectSQLInputException("Impossible connect with database", e);
        }
    }
}
