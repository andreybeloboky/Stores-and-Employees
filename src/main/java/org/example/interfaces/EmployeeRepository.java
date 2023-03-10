package org.example.interfaces;

import org.example.modal.Employee;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public interface EmployeeRepository {
    void add(Employee employee);

    Optional<Employee> load(int id);

    boolean remove(int id);

    ArrayList<Employee> loadAllEmployees();

}
