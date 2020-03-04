package com.sydoriv.flight_search_bot.client;

import com.sydoriv.flight_search_bot.domain.geocoding.LatitudeLongitudeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${gcp.maps.url}", name = "geocodingClient")
public interface GeocodingApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/geocode/json")
    LatitudeLongitudeResponse convertLocationToLatitudeLongitudeValue(@RequestParam("address") String address,
        @RequestParam("key") String key);

}
