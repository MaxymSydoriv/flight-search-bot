package com.sydoriv.flight_search_bot.domain.geocoding;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class LatitudeLongitudeResponse {

    @JsonProperty("results")
    private List<CityLocationAndProperties> cityLocationAndProperties;
    @JsonProperty("status")
    private String status;
}
