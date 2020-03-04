package com.sydoriv.flight_search_bot.domain.lufthansa.search.airport;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Airport {

    @JsonProperty("AirportCode")
    private String airportCode;
    @JsonProperty("Position")
    private Position position;
    @JsonProperty("CityCode")
    private String cityCode;
    @JsonProperty("CountryCode")
    private String countryCode;
    @JsonProperty("Names")
    private Names names;

    @JsonProperty
    private Distance distance;

}
