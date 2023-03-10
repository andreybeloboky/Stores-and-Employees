package org.example.interfaces;

import org.example.modal.Store;

import java.util.Optional;

public interface StoreRepository {
    void add(Store store);

    int remove(int id);

    Optional<Store> loadById(int id);

}
