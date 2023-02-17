package org.example;

public class Store {

    private int id;
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

    public String getTown() {
        return town;
    }


    public String getNameOfStore() {
        return nameOfStore;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", town='" + town + '\'' +
                ", nameOfStore='" + nameOfStore + '\'' +
                '}';
    }
}
