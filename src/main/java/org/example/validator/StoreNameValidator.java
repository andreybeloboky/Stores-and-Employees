package org.example.validator;

import org.example.exception.NoSuchEntityException;

import java.util.regex.Pattern;

public class StoreNameValidator {

    private static final String NAME_PATTERN = "HM\\d{3}";

    /**
     * @param str - name of store.
     */
    public void validate(String str) {
        var name = Pattern.compile(NAME_PATTERN);
        var checkNameOfStore = name.matcher(str);
        if (!checkNameOfStore.matches()) {
            throw new NoSuchEntityException("Name " + str + " is incorrect");
        }
    }
}
