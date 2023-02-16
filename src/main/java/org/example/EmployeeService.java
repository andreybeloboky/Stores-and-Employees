package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeService {

    private static final DatabaseRepository databaseRepository = new DatabaseRepository();
    private static final UncoverJSONService uncoverJSONService = new UncoverJSONService();

    /**
     * @param id
     */
    public void deleteEmployee(int id) {

    }


    public void addEmployee(String s) {
        String[] emp = uncoverJSONService.uncoverJSON(s);
   //     databaseRepository.insert(emp);
    }

    /**
     * @param id
     */
    public void getInfoFromIdEmployee(int id) {

    }
}
