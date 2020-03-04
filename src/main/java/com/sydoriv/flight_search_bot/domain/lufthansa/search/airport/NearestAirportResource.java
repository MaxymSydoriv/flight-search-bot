package com.sydoriv.flight_search_bot.domain.lufthansa.search.airport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NearestAirportResource {
    @JsonProperty("Airports")
    private AirportContainer airportContainer;
    @JsonProperty("Meta")
    HashMap<String, Object> metadata;
}
