package org.example.service;

import org.example.exception.NoSuchEntityException;
import org.example.interfaces.StoreServiceImplementation;
import org.example.modal.Store;
import org.example.dto.StoreCreateCommand;
import org.example.repository.StoreDatabaseRepository;
import org.example.validator.StoreNameValidator;


public class StoreService implements StoreServiceImplementation {

    private final StoreDatabaseRepository storeDatabaseRepository = new StoreDatabaseRepository();
    private final StoreNameValidator storeNameValidator = new StoreNameValidator();

    /**
     * @param store which is needed to insert to DB
     */
    public void add(StoreCreateCommand store) {
        storeNameValidator.validate(store.getNameOfStore());
        var storeOrigin = new Store(store.getTown(), store.getNameOfStore());
        storeDatabaseRepository.add(storeOrigin);
    }

    /**
     * @param id which is needed to delete
     */
    public void remove(int id) {
        if (!storeDatabaseRepository.remove(id)) {
            throw new NoSuchEntityException("There isn't such a id store: " + id);
        }
    }

    /**
     * @param id which is needed to get
     * @return object store
     */
    public Store getInfoFromIdStore(int id) {
        var store = storeDatabaseRepository.add(id);
        if (store == null) {
            throw new NoSuchEntityException("There isn't such store id: " + id);
        } else {
            return store;
        }
    }
}
