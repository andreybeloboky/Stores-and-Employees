package org.example;


import java.awt.*;
import java.util.Scanner;

public class ConsoleController {

    private static final EmployeeService employeeService = new EmployeeService();
    private static final StoreService storeService = new StoreService();

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
            System.out.println("7 - get summa salary from every stores.");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("Could you give me some information?");
                    System.out.println("Store where this person works");
                    // will do checking numbers of stores.whether existing or not.
                    int storeNumber = scanner.nextInt();
                    System.out.println("First name");
                    String firstName = scanner.next();
                    System.out.println("Last name");
                    String lastName = scanner.next();
                    // will do checking numbers of stores.whether existing or not.
                    System.out.println("Position");
                    String position = scanner.next();
                    System.out.println("Salary");
                    int salary = scanner.nextInt();
                    String exampleOfJSON = "{" + "\"firstName\":" + firstName + ",\"lastName\":" + lastName + ",\"position\":" + position + ",\"salary\":" + salary + ",\"store\":" + storeNumber + "}";
                    employeeService.addEmployee(exampleOfJSON);
                }
                case 2 -> {
                    System.out.println("What's id you want to delete?");
                    int id = scanner.nextInt();
                    employeeService.deleteEmployee(id);
                }
                case 3 -> {
                    System.out.println("What's id you want to get?");
                    int id = scanner.nextInt();
                    employeeService.getInfoFromIdEmployee(id);
                }
                case 4 -> {
                    System.out.println("Could you give me some information?");
                    System.out.println("Name of this store");
                    // check, this name can be starting with HM
                    String storeName = scanner.next();
                    System.out.println("Town where this store is located");
                    String town = scanner.next();
                    storeService.addStore(storeName, town);
                }
                case 5 -> {
                    System.out.println("What's id you want to delete?");
                    int id = scanner.nextInt();
                    storeService.deleteStore(id);
                }
                case 6 -> {
                    System.out.println("What's id you want to get?");
                    int id = scanner.nextInt();
                    storeService.getInfoFromIdStore(id);
                }
            }
        } else if (tap == 2) {
            System.out.println("Finish. Thank you for spending your time here.");
        } else {
            throw new IncorrectException("Wow, man, your number isn't 1 or 2.");
        }
    }
}
