package org.example.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({"town", "nameOfStore"})
public class StoreCreateCommand {
    @JsonProperty("town")
    private String town;
    @JsonProperty("nameOfStore")
    private String nameOfStore;

    public String getTown() {
        return town;
    }

    public String getNameOfStore() {
        return nameOfStore;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreCreateCommand that = (StoreCreateCommand) o;
        return Objects.equals(town, that.town) && Objects.equals(nameOfStore, that.nameOfStore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(town, nameOfStore);
    }
}
