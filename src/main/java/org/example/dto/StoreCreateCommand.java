package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@JsonPropertyOrder({"town", "nameOfStore"})
@Getter
@EqualsAndHashCode
public class StoreCreateCommand {
    @JsonProperty("town")
    private String town;
    @JsonProperty("nameOfStore")
    private String nameOfStore;

}
