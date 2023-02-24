package org.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.NoSuchEntityException;
import org.example.modal.Employee;
import org.example.modal.Store;
import org.example.dto.EmployeeCreateCommand;
import org.example.dto.StoreCreateCommand;
import org.example.service.EmployeeService;
import org.example.service.StoreService;

import java.util.Scanner;

@Slf4j
public class ConsoleController {
    private static final EmployeeService employeeService = new EmployeeService();
    private static final StoreService storeService = new StoreService();
    private static final ConsoleController controller = new ConsoleController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        log.info("Hi user!");
        log.info("You got into the store's database.");
        log.info("What do you want to do?");
        var operator = 0;
        while (operator == 0) {
            log.info("Tap number 1, if you want to get list of functions. Tap number 2, if you want to end this seance.");
            var tap = scanner.nextInt();
            if (tap == 1) {
                log.info("Your next step is to choose one of the option");
                log.info("Here is list of function what you can do.");
                log.info("1-4 - employee, 5-9 - store.");
                log.info("1 - add employee to database.");
                log.info("2 - remove employee to database.");
                log.info("3 - get information of employee by ID.");
                log.info("4 - add store to database.");
                log.info("5 - remove store to database.");
                log.info("6 - get information of store by ID.");
                log.info("7 - get summa salary from every employees.");
                var option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        log.info("Enter JSON string");
                        var exampleOfJSON = scanner.next();
                        EmployeeCreateCommand employee = controller.getEmployeeObject(exampleOfJSON);
                        employeeService.save(employee);
                    }
                    case 2 -> {
                        log.info("What's id you want to delete?");
                        var id = scanner.nextInt();
                        employeeService.deleteEmployee(id);
                    }
                    case 3 -> {
                        log.info("What's id you want to get?");
                        var id = scanner.nextInt();
                        Employee emp = employeeService.getInfoFromIdEmployee(id);
                        System.out.println(emp.toString());
                    }
                    case 4 -> {
                        log.info("Could you give me some information?");
                        log.info("Name of this store");
                        log.info("Beginning with HM, ending number of store");
                        log.info("Town where this store is located");
                        var json = scanner.next();
                        StoreCreateCommand store = controller.getStoreObject(json);
                        storeService.add(store);
                    }
                    case 5 -> {
                        log.info("What's id you want to delete?");
                        var id = scanner.nextInt();
                        storeService.remove(id);
                    }
                    case 6 -> {
                        log.info("What's id you want to get?");
                        var id = scanner.nextInt();
                        Store store = storeService.getInfoFromIdStore(id);
                        System.out.println(store);
                    }
                    case 7 -> log.info(String.valueOf(employeeService.getAllSalaryOfEmployees().getSum()));

                }
            } else if (tap == 2) {
                operator = 1;
                log.info("Finish. Thank you for spending your time here.");
            } else {
                throw new NoSuchEntityException("Wow, man, your number isn't 1 or 2.");
            }
        }
    }

    /**
     * @param json input and then object output. User gives it.
     * @return employee object from json format.
     */
    private EmployeeCreateCommand getEmployeeObject(String json) {
        try {
            var objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, EmployeeCreateCommand.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param json input and then object output. User gives it.
     * @return store object from json format.
     */
    private StoreCreateCommand getStoreObject(String json) {
        try {
            var objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, StoreCreateCommand.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
