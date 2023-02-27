package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({"town", "nameOfStore"})
@Data
public class StoreCreateCommand {
    @JsonProperty("town")
    private String town;
    @JsonProperty("nameOfStore")
    private String nameOfStore;

}
