package org.example.modal;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Store {
    private int id;
    @NonNull private final String town;
    @NonNull private final String nameOfStore;
}
