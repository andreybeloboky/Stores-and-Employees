package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.NoSuchEntityException;
import org.example.dto.EmployeeCreateCommand;
import org.example.dto.StoreCreateCommand;
import org.example.modal.Store;
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
        System.out.println("Hi user!");
        System.out.println("You got into the store's database.");
        System.out.println("What do you want to do?");
        var operator = 0;
        while (operator == 0) {
            System.out.println("Tap number 1, if you want to get list of functions. Tap number 2, if you want to end this seance.");
            var tap = scanner.nextInt();
            if (tap == 1) {
                System.out.println("Your next step is to choose one of the option");
                System.out.println("Here is list of function what you can do.");
                System.out.println("1-4 - employee, 5-9 - store.");
                System.out.println("1 - add employee to database.");
                System.out.println("2 - remove employee to database.");
                System.out.println("3 - get information of employee by ID.");
                System.out.println("4 - add store to database.");
                System.out.println("5 - remove store to database.");
                System.out.println("6 - get information of store by ID.");
                System.out.println("7 - get summa salary from every employees.");
                log.info("User chose option who needs him/her");
                var option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("Enter JSON string");
                        var exampleOfJSON = scanner.next();
                        var employee = controller.convertEmployeeObject(exampleOfJSON);
                        log.info("Convert employee object {} and send it to save method to DB", employee);
                        employeeService.save(employee);
                    }
                    case 2 -> {
                        System.out.println("What's id you want to delete?");
                        var id = scanner.nextInt();
                        log.info("Load id and send it to delete method {}", id);
                        employeeService.deleteEmployee(id);
                    }
                    case 3 -> {
                        System.out.println("What's id you want to get?");
                        var id = scanner.nextInt();
                        log.info("Load id {} and send it so that will get inform by id method", id);
                        var emp = employeeService.loadInfoFromIdEmployee(id);
                        System.out.println(emp.toString());
                    }
                    case 4 -> {
                        System.out.println("Could you give me some information?");
                        System.out.println("Name of this store");
                        System.out.println("Beginning with HM, ending number of store");
                        System.out.println("Town where this store is located");
                        var json = scanner.next();
                        var store = controller.getStoreObject(json);
                        log.info("Load store object {} and send it to add method to DB", store);
                        storeService.add(store);
                    }
                    case 5 -> {
                        System.out.println("What's id you want to delete?");
                        var id = scanner.nextInt();
                        log.info("Get id {} and send it to delete method ", id);
                        storeService.remove(id);
                    }
                    case 6 -> {
                        System.out.println("What's id you want to get?");
                        var id = scanner.nextInt();
                        log.info("Get id {} and send it so that will get inform by id method", id);
                        Store store = storeService.getInfoFromIdStore(id);
                        System.out.println(store);
                    }
                    case 7 -> {
                        log.info("The salaries of everyone's employees have been calculated");
                        System.out.println(employeeService.loadAllSalaryOfEmployees().getSum());
                    }

                }
            } else if (tap == 2) {
                operator = 1;
                System.out.println("Finish. Thank you for spending your time here.");
            } else {
                throw new NoSuchEntityException("Wow, man, your number isn't 1 or 2.");
            }
        }
    }

    /**
     * @param json input and then object output. User gives it.
     * @return employee object from json format.
     */
    @SneakyThrows
    private EmployeeCreateCommand convertEmployeeObject(String json) {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, EmployeeCreateCommand.class);
    }

    /**
     * @param json input and then object output. User gives it.
     * @return store object from json format.
     */
    @SneakyThrows
    private StoreCreateCommand getStoreObject(String json) {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, StoreCreateCommand.class);
    }
}
