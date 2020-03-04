package com.sydoriv.flight_search_bot.service;

import com.sydoriv.flight_search_bot.client.GeocodingApiClient;
import com.sydoriv.flight_search_bot.domain.geocoding.LatitudeLongitudeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class GeocodingService {

    private final GeocodingApiClient geocodingApiClient;

    @Value("${gcp.maps.geocode.apikey}")
    private String geocodingApiKey;

    public LatitudeLongitudeResponse convertCityToLatitudeLongitude(String cityName) {
        return geocodingApiClient.convertLocationToLatitudeLongitudeValue(cityName, geocodingApiKey);
    }
}

