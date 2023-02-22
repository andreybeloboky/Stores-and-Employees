package org.example.service;

import org.example.exception.NoSuchEntityException;
import org.example.modal.Employee;
import org.example.dto.EmployeeCreateCommand;
import org.example.modal.Store;
import org.example.repository.EmployeeDatabaseRepository;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService {
    private final EmployeeDatabaseRepository employeeDatabaseRepository = new EmployeeDatabaseRepository();
    private final StoreService storeService = new StoreService();

    /**
     * @param id - get id from user.
     */
    public void deleteEmployee(int id) {
        if (!employeeDatabaseRepository.remove(id)) {
            throw new NoSuchEntityException("There isn't such a employee id:" + id);
        }
    }

    /**
     * @param id which need to got from user.
     * @return employee object
     */
    public Employee getInfoFromIdEmployee(int id) {
        Employee employee = employeeDatabaseRepository.load(id);
        Optional<Employee> employeeOptional = Optional.ofNullable(employee);
        if (employeeOptional.isPresent()) {
            throw new NoSuchEntityException("There isn't such employee id: " + id);
        } else {
            return employee;
        }
    }

    /**
     * @param createCommand which got from JSON.
     */
    public void save(EmployeeCreateCommand createCommand) {
        Store store = storeService.getInfoFromIdStore(createCommand.getStoreId());
        Employee employee = new Employee(createCommand.getFirstName(), createCommand.getLastName(),
                createCommand.getPosition(), createCommand.getSalary(), store);
        employeeDatabaseRepository.add(employee);
    }

    /**
     * @return summa of all employees
     */
    public IntSummaryStatistics getAllSalaryOfEmployees() {
        ArrayList<Employee> employees = employeeDatabaseRepository.loadAllEmployees();
        return employees.stream().collect(Collectors.summarizingInt(Employee::getSalary));
    }
}
