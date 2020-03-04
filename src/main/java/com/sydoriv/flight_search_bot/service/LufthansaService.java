package com.sydoriv.flight_search_bot.service;

import com.sydoriv.flight_search_bot.client.LufthansaClient;
import com.sydoriv.flight_search_bot.domain.lufthansa.LufthansaCredentials;
import com.sydoriv.flight_search_bot.domain.lufthansa.PassengerFlightSearchRequest;
import com.sydoriv.flight_search_bot.domain.lufthansa.search.airport.NearestAirportSearchResponse;
import com.sydoriv.flight_search_bot.domain.lufthansa.search.flight.FlightSearchResponseEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class LufthansaService {

    private String lufthansaApiAuthToken;

    private final LufthansaCredentials lufthansaCredentials;

    private final LufthansaClient lufthansaClient;

    @Scheduled(cron = "0 */30 * * * *")
    void refreshToken() {
        this.lufthansaApiAuthToken =
            "Bearer " + lufthansaClient.retrieveToken(this.lufthansaCredentials).getAccessToken();
    }

    @PostConstruct
    void init() {
        refreshToken();
    }

    public List<FlightSearchResponseEntity> searchFlights(PassengerFlightSearchRequest searchRequest) {
        Map<String, String> headers = prepareRequiredHeaders();
        return lufthansaClient.searchFlights(searchRequest, headers);
    }

    public NearestAirportSearchResponse findNearestAirports(String latlng) {
        return lufthansaClient.searchNearestAirports(latlng, "en", prepareRequiredHeaders());
    }


    private Map<String, String> prepareRequiredHeaders() {
        Map<String, String> requiredHeaders = new HashMap<>();
        requiredHeaders.put(HttpHeaders.AUTHORIZATION, lufthansaApiAuthToken);
        return requiredHeaders;
    }


}
