package org.example.interfaces;

import org.example.dto.StoreCreateCommand;
import org.example.modal.Store;

public interface StoreServiceImplementation {
    void add(StoreCreateCommand store);
    void remove(int id);
    Store getInfoFromIdStore(int id);
}
