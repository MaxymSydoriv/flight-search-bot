package com.sydoriv.flight_search_bot.domain.geocoding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Geometry {

    @JsonProperty("location")
    private Coordinate location;
    @JsonProperty("location_type")
    private String locationType;

    public String getFormattedLatitudeLongitude() {
        return String.format("{%s, %s}", location.getLatitude(), location.getLongitude());
    }
}
