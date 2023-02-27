package org.example.modal;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Store {
    private int id;
    private final String town;
    private final String nameOfStore;
}
