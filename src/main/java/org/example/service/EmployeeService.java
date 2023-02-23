package org.example.service;

import org.example.exception.NoSuchEntityException;
import org.example.interfaces.EmployeeServiceImplementation;
import org.example.modal.Employee;
import org.example.dto.EmployeeCreateCommand;
import org.example.repository.EmployeeDatabaseRepository;

import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService implements EmployeeServiceImplementation {
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
        var employee = employeeDatabaseRepository.load(id);
        var employeeOptional = Optional.ofNullable(employee);
        if (employeeOptional.isEmpty()) {
           throw new NoSuchEntityException("There isn't such employee id: " + id);
        } else {
            return employee;
        }
    }

    /**
     * @param createCommand which got from JSON.
     */
    public void save(EmployeeCreateCommand createCommand) {
        var store = storeService.getInfoFromIdStore(createCommand.getStoreId());
        var employee = new Employee(createCommand.getFirstName(), createCommand.getLastName(),
                createCommand.getPosition(), createCommand.getSalary(), store);
        employeeDatabaseRepository.add(employee);
    }

    /**
     * @return summa of all employees
     */
    public IntSummaryStatistics getAllSalaryOfEmployees() {
        var employees = employeeDatabaseRepository.loadAllEmployees();
        return employees.stream().collect(Collectors.summarizingInt(Employee::getSalary));
    }
}
