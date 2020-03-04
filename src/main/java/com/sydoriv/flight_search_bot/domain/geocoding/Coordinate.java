package com.sydoriv.flight_search_bot.domain.geocoding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Coordinate {

    @JsonProperty("lat")
    private Double latitude;
    @JsonProperty("lng")
    private Double longitude;
}
