package org.example;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private Position position;
    private int salary;
    private Store store;

    public Employee(int id, String firstName, String lastName, Position position, int salary, Store store) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Position getPosition() {
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
                ", position=" + position +
                ", salary=" + salary +
                ", id store=" + store.getId() + " " + ", name of store: " + store.getNameOfStore() + ", town: " + store.getTown() + "}";
    }
}
