package com.sydoriv.flight_search_bot.service;

import com.botscrew.botframework.annotation.Intent;
import com.botscrew.botframework.annotation.IntentProcessor;
import com.botscrew.botframework.annotation.Original;
import com.botscrew.messengercdk.service.Sender;
import com.botscrew.nlpclient.provider.dialogflow.v2.model.QueryResult;
import com.botscrew.nlpclient.provider.dialogflow.v2.model.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.sydoriv.flight_search_bot.domain.RequestStatus;
import com.sydoriv.flight_search_bot.domain.geocoding.CityLocationAndProperties;
import com.sydoriv.flight_search_bot.domain.geocoding.Geometry;
import com.sydoriv.flight_search_bot.domain.lufthansa.PassengerFlightSearchRequest;
import com.sydoriv.flight_search_bot.domain.lufthansa.search.flight.FlightSearchResponseEntity;
import com.sydoriv.flight_search_bot.model.ChatUserImpl;
import com.sydoriv.flight_search_bot.model.CurrentSearchParameters;
import com.sydoriv.flight_search_bot.repository.CurrentSearchParametersRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@IntentProcessor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchIntentService {

    private final Sender sender;
    private final GeocodingService geocodingService;
    private final LufthansaService lufthansaService;
    private final CurrentSearchParametersRepository searchParametersRepository;

    @Intent(value = "search_flight_schedule")
    public void checkParamsAndSearch(ChatUserImpl chatUser, @Original Response response) {
        QueryResult queryResult = response.getQueryResult();
        String fulfilmentMessage = queryResult.getFulfillmentText();
        CurrentSearchParameters searchParameters = searchParametersRepository
            .findByChatUserIdAndStatus(chatUser.getId(), RequestStatus.PENDING).stream().findFirst()
            .orElse(CurrentSearchParameters.emptyWithStatus(RequestStatus.PENDING));

        if (queryResult.getAllRequiredParamsPresent()) {
            fillSearchParametersEntity(queryResult.getParameters(), searchParameters);
            searchParameters.setRequestStatus(RequestStatus.WAITING_FOR_EXTENDED_SEARCH);
            List<FlightSearchResponseEntity> flightSearchResponse = lufthansaService
                .searchFlights(createFlightSearchRequest(searchParameters));
            sender.send(chatUser, flightSearchResponse.toString());
        }
        sender.send(chatUser, fulfilmentMessage);
    }

    private void fillSearchParametersEntity(Map<String, JsonNode> retrievedParameters,
        CurrentSearchParameters currentSearchParameters) {
        String departure = getStringValueFromParametersMap(retrievedParameters, "departure");
        String arrival = getStringValueFromParametersMap(retrievedParameters, "arrival");
        LocalDateTime dateInUTC = Optional.ofNullable(getStringValueFromParametersMap(retrievedParameters, "date"))
            .map(dateString -> ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME))
            .map(zonedDateTime -> zonedDateTime.withZoneSameInstant(ZoneId.of("UTC")))
            .map(ZonedDateTime::toLocalDateTime).orElse(null);

        currentSearchParameters.setDeparture(departure);
        currentSearchParameters.setArrival(arrival);
        currentSearchParameters.setDate(dateInUTC);
    }

    private String getStringValueFromParametersMap(Map<String, JsonNode> parameters, String paramName) {
        return Optional.ofNullable(parameters)
            .map(params -> parameters.get(paramName))
            .map(JsonNode::asText)
            .orElse(null);
    }

    private PassengerFlightSearchRequest createFlightSearchRequest(CurrentSearchParameters searchParameters) {
        String nearestDepartureAirport = findNearestAirport(searchParameters.getDeparture());
        String nearestArrivalAirport = findNearestAirport(searchParameters.getArrival());
        PassengerFlightSearchRequest flightSearchRequest = new PassengerFlightSearchRequest();
        flightSearchRequest.setDaysOfOperation("1234567");
        flightSearchRequest.setTimeMode("UTC");
        flightSearchRequest.setOrigin(nearestDepartureAirport);
        flightSearchRequest.setDestination(nearestArrivalAirport);
        flightSearchRequest.setAirlines("LH");
        flightSearchRequest.setStartDate(searchParameters.getDate().toLocalDate().toString());
        flightSearchRequest.setEndDate(searchParameters.getDate().toLocalDate().toString());
        return flightSearchRequest;

    }

    private String findNearestAirport(String cityName) {
        String formattedLatLng = geocodingService.convertCityToLatitudeLongitude(cityName)
            .getCityLocationAndProperties()
            .stream().findFirst()
            .map(CityLocationAndProperties::getGeometry)
            .map(Geometry::getFormattedLatitudeLongitude)
            .orElseThrow(() -> new IllegalArgumentException("Could not find city"));

        return lufthansaService.findNearestAirports(formattedLatLng).getNearestAirportCode();
    }
}
