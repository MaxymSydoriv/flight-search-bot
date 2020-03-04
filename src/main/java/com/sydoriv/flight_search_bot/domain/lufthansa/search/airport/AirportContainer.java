package com.sydoriv.flight_search_bot.domain.lufthansa.search.airport;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;
import lombok.Data;

@Data
public class AirportContainer {

    @JsonProperty("Airport")
    private List<Airport> airports;

}
