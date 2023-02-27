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
        if(employeeDatabaseRepository.remove(id) == 0){
            throw new NoSuchEntityException("There is not such a employee");
        }
        log.info("The employee has been deleted");
    }

    /**
     * @param id which need to got from user.
     * @return employee object
     */
    public Employee getInfoFromIdEmployee(int id) {
        return employeeDatabaseRepository.load(id);
    }

    /**
     * @param createCommand which got from JSON.
     */
    public void save(EmployeeCreateCommand createCommand) {
        var store = storeService.getInfoFromIdStore(createCommand.getStoreId());
        var employee = new Employee(createCommand.getFirstName(), createCommand.getLastName(),
                createCommand.getPosition(), createCommand.getSalary(), store);
        employeeDatabaseRepository.add(employee);
        log.info("The employee has been saved");
    }

    /**
     * @return summa of all employees
     */
    public IntSummaryStatistics getAllSalaryOfEmployees() {
        var employees = employeeDatabaseRepository.loadAllEmployees();
        return employees.stream().collect(Collectors.summarizingInt(Employee::getSalary));
    }
}
