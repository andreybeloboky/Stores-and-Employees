package org.example.modal;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {
    private int id;
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;
    @NonNull
    private final String position;
    @NonNull
    private final Integer salary;
    @NonNull
    private final Store store;
}
