package org.example.modal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Employee {

    private int id;
    private final String firstName;
    private final String lastName;
    private final String position;
    private final int salary;
    private final Store store;

    public Employee(String firstName, String lastName, String position, int salary, Store store) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.store = store;
    }

    public Employee(int id, String firstName, String lastName, String position, int salary, Store store) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.store = store;
    }
}
