package org.example.service;

import org.example.exception.NoSuchException;
import org.example.modal.Employee;
import org.example.modal.EmployeeCreateCommand;
import org.example.modal.Store;
import org.example.repository.EmployeeDatabaseRepository;

import java.util.ArrayList;

public class EmployeeService {
    private final EmployeeDatabaseRepository databaseRepository = new EmployeeDatabaseRepository();

    private final StoreService storeService = new StoreService();

    /**
     * @param id - get id from user.
     */
    public void deleteEmployee(int id) {
        if (!databaseRepository.delete(id)) {
            throw new NoSuchException("There isn't such a employee");
        }
    }

    /**
     * @param id which need to get from user.
     * @return employee object
     */
    public Employee getInfoFromIdEmployee(int id) {
        Employee employee = databaseRepository.select(id);
        if (employee == null) {
            throw new NoSuchException("There isn't such employee");
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
        databaseRepository.insert(employee);
    }

    /**
     * @return summa of all employees
     */
    public int getAllSalaryOfEmployees() {
        ArrayList<Integer> salaryOfEmployee = databaseRepository.salary();
        int summa = 0;
        for (Integer sum : salaryOfEmployee) {
            summa += sum;
        }
        return summa;
    }
}
