package com.sydoriv.flight_search_bot.domain.lufthansa.search.airport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NearestAirportSearchResponse {

    @JsonProperty("NearestAirportResource")
    private NearestAirportResource nearestAirportResource;

    public String getNearestAirportCode() {
        return Optional.ofNullable(nearestAirportResource)
            .map(NearestAirportResource::getAirportContainer)
            .map(AirportContainer::getAirports)
            .map(Collection::stream)
            .map(Stream::findFirst)
            .map(airport -> airport.map(Airport::getAirportCode))
            .flatMap(airportCode -> airportCode)
            .orElse(null);

    }
}
