package org.example;

public class Store {

    private int id;
    private Employee[] numbersOfEmployees;
    private String town;

    private String nameOfStore;


    public Store(int id, String town, String nameOfStore) {
        this.id = id;
        this.town = town;
        this.nameOfStore = nameOfStore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee[] getNumbersOfEmployees() {
        return numbersOfEmployees;
    }

    public void setNumbersOfEmployees(Employee[] numbersOfEmployees) {
        this.numbersOfEmployees = numbersOfEmployees;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getNameOfStore() {
        return nameOfStore;
    }

    public void setNameOfStore(String nameOfStore) {
        this.nameOfStore = nameOfStore;
    }
}
