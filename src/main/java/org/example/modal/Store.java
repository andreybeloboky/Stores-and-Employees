package org.example.modal;

import java.util.Objects;

public class Store {
    private int id;
    private final String town;
    private final String nameOfStore;

    public Store(String town, String nameOfStore) {
        this.town = town;
        this.nameOfStore = nameOfStore;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return id == store.id && Objects.equals(town, store.town) && Objects.equals(nameOfStore, store.nameOfStore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, town, nameOfStore);
    }
}
