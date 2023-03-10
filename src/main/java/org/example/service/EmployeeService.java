package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.NoSuchEntityException;
import org.example.interfaces.EmployeeServiceImplementation;
import org.example.modal.Employee;
import org.example.dto.EmployeeCreateCommand;
import org.example.repository.EmployeeDatabaseRepository;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

@Slf4j
public class EmployeeService implements EmployeeServiceImplementation {
    private final EmployeeDatabaseRepository employeeDatabaseRepository = new EmployeeDatabaseRepository();
    private final StoreService storeService = new StoreService();

    /**
     * @param id - get id from user.
     */
    public void deleteEmployee(int id) {
        if (!employeeDatabaseRepository.remove(id)) {
            throw new NoSuchEntityException("There is not such a employee");
        }
        log.info("Delete id and send it to delete method {}", id);
    }

    /**
     * @param id which need to got from user.
     * @return employee object
     */
    public Employee loadInfoFromIdEmployee(int id) {
        log.info("Getting employee object id: {}, if we get empty object, we will get NoSuchEntityException", id);
        return employeeDatabaseRepository.load(id).orElseThrow(() -> new NoSuchEntityException("There isn't such employee id: " + id));
    }

    /**
     * @param createCommand which got from JSON.
     */
    public void save(EmployeeCreateCommand createCommand) {
        final var store = storeService.getInfoFromIdStore(createCommand.getStoreId());
        final var employee = new Employee(createCommand.getFirstName(), createCommand.getLastName(),
                createCommand.getPosition(), createCommand.getSalary(), store);
        employeeDatabaseRepository.add(employee);
        log.info("The employee has been saved {}", employee);
    }

    /**
     * @return summa of all employees
     */
    public IntSummaryStatistics loadAllSalaryOfEmployees() {
        final var employees = employeeDatabaseRepository.loadAllEmployees();
        return employees.stream().collect(Collectors.summarizingInt(Employee::getSalary));
    }
}
