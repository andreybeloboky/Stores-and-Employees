package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StoreNameValidator {

    private static final String PATTERN_FIND_NAME = "HM\\d{3}";

    /**
     * @param str - name of store.
     * @return true or exception.
     */
    public boolean validate(String str) {
        Pattern email = Pattern.compile(PATTERN_FIND_NAME);
        Matcher checkEmail = email.matcher(str);
        if (!checkEmail.matches()) {
            throw new IncorrectException("Name " + str + " is incorrect");
        } else {
            return true;
        }
    }
}
