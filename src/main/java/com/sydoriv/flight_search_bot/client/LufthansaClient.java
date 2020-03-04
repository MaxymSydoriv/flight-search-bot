package com.sydoriv.flight_search_bot.client;

import com.sydoriv.flight_search_bot.config.FeignClientConfig;
import com.sydoriv.flight_search_bot.domain.lufthansa.LufthansaAuthResponse;
import com.sydoriv.flight_search_bot.domain.lufthansa.LufthansaCredentials;
import com.sydoriv.flight_search_bot.domain.lufthansa.PassengerFlightSearchRequest;
import com.sydoriv.flight_search_bot.domain.lufthansa.search.airport.NearestAirportSearchResponse;
import com.sydoriv.flight_search_bot.domain.lufthansa.search.flight.FlightSearchResponseEntity;
import feign.Headers;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${lufthansa.api.url}", name = "lufthansaClient", configuration = FeignClientConfig.class)
public interface LufthansaClient {

    @RequestMapping(method = RequestMethod.POST, value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    LufthansaAuthResponse retrieveToken(LufthansaCredentials credentials);

    @RequestMapping(method = RequestMethod.GET, value = "/flight-schedules/flightschedules/passenger")
    List<FlightSearchResponseEntity> searchFlights(@SpringQueryMap PassengerFlightSearchRequest searchRequest,
        @RequestHeader Map<String, String> headers);

    @RequestMapping(method = RequestMethod.GET, value = "/references/airports/nearest/{latlng}")
    NearestAirportSearchResponse searchNearestAirports(@PathVariable("latlng") String latlng,
        @RequestParam(value = "lang", required = false, defaultValue = "en") String lang,
        @RequestHeader Map<String, String> headers);
}
