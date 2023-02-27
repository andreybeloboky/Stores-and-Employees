package org.example.interfaces;

import org.example.dto.EmployeeCreateCommand;
import org.example.modal.Employee;

import java.util.IntSummaryStatistics;

public interface EmployeeServiceImplementation {
    void deleteEmployee(int id);

    Employee loadInfoFromIdEmployee(int id);

    void save(EmployeeCreateCommand createCommand);

    IntSummaryStatistics loadAllSalaryOfEmployees();
}
