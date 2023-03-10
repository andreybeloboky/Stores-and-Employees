package org.example.modal;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {
    private int id;
    private final String firstName;
    private final String lastName;
    private final String position;
    private final Integer salary;
    private final Store store;
}
