package org.example.modal;

import java.util.Objects;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public Store getStore() {
        return store;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", store=" + store +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && salary == employee.salary && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(position, employee.position) && Objects.equals(store, employee.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, position, salary, store);
    }
}
