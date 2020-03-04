package com.sydoriv.flight_search_bot.domain.lufthansa.search.airport;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Distance {

    @JsonProperty("Value")
    private Integer value;
    @JsonProperty("UOM")
    private String uom;
}
