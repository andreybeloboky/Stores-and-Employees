package org.example.modal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
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
}
