package org.example.interfaces;

import org.example.dto.EmployeeCreateCommand;
import org.example.modal.Employee;

import java.util.IntSummaryStatistics;
import java.util.Optional;

public interface EmployeeServiceImplementation {
    void deleteEmployee(int id);

    Employee getInfoFromIdEmployee(int id);

    void save(EmployeeCreateCommand createCommand);

    IntSummaryStatistics getAllSalaryOfEmployees();
}
