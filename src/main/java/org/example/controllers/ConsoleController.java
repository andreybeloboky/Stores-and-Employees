package org.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.NoSuchException;
import org.example.modal.Employee;
import org.example.modal.Store;
import org.example.modal.EmployeeCreateCommand;
import org.example.modal.StoreCreateCommand;
import org.example.service.EmployeeService;
import org.example.service.StoreService;

import java.util.Scanner;

public class ConsoleController {

    private static final EmployeeService employeeService = new EmployeeService();
    private static final StoreService storeService = new StoreService();
    private static final ConsoleController controller = new ConsoleController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi user!");
        System.out.println("You got into the store's database.");
        System.out.println("What do you want to do?");
        System.out.println("Tap number 1, if you want to get list of functions. Tap number 2, if you want to end this seance.");
        int tap = scanner.nextInt();
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
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("Enter JSON string");
                    String exampleOfJSON = scanner.next();
                    EmployeeCreateCommand employee = controller.getEmployeeObject(exampleOfJSON);
                    employeeService.save(employee);
                }
                case 2 -> {
                    System.out.println("What's id you want to delete?");
                    int id = scanner.nextInt();
                    employeeService.deleteEmployee(id);
                }
                case 3 -> {
                    System.out.println("What's id you want to get?");
                    int id = scanner.nextInt();
                    Employee emp = employeeService.getInfoFromIdEmployee(id);
                    System.out.println(emp.toString());
                }
                case 4 -> {
                    System.out.println("Could you give me some information?");
                    System.out.println("Name of this store");
                    System.out.println("Beginning with HM, ending number of store");
                    System.out.println("Town where this store is located");
                    String json = scanner.next();
                    StoreCreateCommand store = controller.getStoreObject(json);
                    storeService.save(store);
                }
                case 5 -> {
                    System.out.println("What's id you want to delete?");
                    int id = scanner.nextInt();
                    storeService.delete(id);
                }
                case 6 -> {
                    System.out.println("What's id you want to get?");
                    int id = scanner.nextInt();
                    Store store = storeService.getInfoFromIdStore(id);
                    System.out.println(store);
                }
                case 7 -> System.out.println(employeeService.getAllSalaryOfEmployees());

            }
        } else if (tap == 2) {
            System.out.println("Finish. Thank you for spending your time here.");
        } else {
            throw new NoSuchException("Wow, man, your number isn't 1 or 2.");
        }
    }

    /**
     * @param json JSON string.
     * @return employee object.
     */
    private EmployeeCreateCommand getEmployeeObject(String json) {
        EmployeeCreateCommand employee = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            employee = objectMapper.readValue(json, EmployeeCreateCommand.class);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    private StoreCreateCommand getStoreObject(String json) {
        StoreCreateCommand store = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            store = objectMapper.readValue(json, StoreCreateCommand.class);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return store;
    }
}
