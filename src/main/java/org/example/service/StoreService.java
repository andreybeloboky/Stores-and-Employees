package org.example.service;

import org.example.exception.IncorrectStoreNameException;
import org.example.exception.NoSuchException;
import org.example.modal.Store;
import org.example.modal.StoreCreateCommand;
import org.example.repository.StoreDatabaseRepository;
import org.example.validator.StoreNameValidator;


public class StoreService {

    private final StoreDatabaseRepository databaseRepository = new StoreDatabaseRepository();
    private final StoreNameValidator storeNameValidator = new StoreNameValidator();

    /**
     * @param store which is needed to insert to DB
     */
    public void save(StoreCreateCommand store) {
        if (storeNameValidator.validate(store.getNameOfStore())) {
            Store storeOrigin = new Store(store.getTown(), store.getNameOfStore());
            databaseRepository.insert(storeOrigin);
        } else {
            throw new IncorrectStoreNameException("Incorrect name");
        }
    }

    /**
     * @param id which is needed to delete
     */
    public void delete(int id) {
        if (!databaseRepository.delete(id)) {
            throw new NoSuchException("There isn't such a store");
        }
    }

    /**
     * @param id which is needed to get
     * @return object store
     */
    public Store getInfoFromIdStore(int id) {
        Store store = databaseRepository.select(id);
        if (store == null) {
            throw new NoSuchException("There isn't such employee");
        } else {
            return store;
        }
    }
}
