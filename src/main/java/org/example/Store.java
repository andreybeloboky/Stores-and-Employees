package org.example;

public class Store {

    private int id;
    private Employee[] numbersOfEmployees;
    private int summaOfEmployees;

    public Store(int id, int summaOfEmployees) {
        this.id = id;
        this.summaOfEmployees = summaOfEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSummaOfEmployees() {
        return summaOfEmployees;
    }

    public void setSummaOfEmployees(int summaOfEmployees) {
        this.summaOfEmployees = summaOfEmployees;
    }
}
