package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.NoSuchEntityException;
import org.example.interfaces.StoreServiceImplementation;
import org.example.modal.Store;
import org.example.dto.StoreCreateCommand;
import org.example.repository.StoreDatabaseRepository;
import org.example.validator.StoreNameValidator;

@Slf4j
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
        log.info("The store has been saved");
    }

    /**
     * @param id which is needed to delete
     */
    public void remove(int id) {
        if (storeDatabaseRepository.remove(id) == 0) {
            throw new NoSuchEntityException("There isn't such a id store: " + id);
        }
        log.info("The store has been deleted");
    }

    /**
     * @param id which is needed to get
     * @return object store
     */
    public Store getInfoFromIdStore(int id) {
        return storeDatabaseRepository.loadById(id).orElseThrow(() -> new NoSuchEntityException("There isn't such store id: " + id));
    }
}
