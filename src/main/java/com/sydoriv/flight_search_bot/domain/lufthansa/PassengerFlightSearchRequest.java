package com.sydoriv.flight_search_bot.domain.lufthansa;

import lombok.Data;

@Data
public class PassengerFlightSearchRequest {

    private String airlines;
    private String startDate;
    private String endDate;
    private String daysOfOperation;
    private String timeMode;
    private String origin;
    private String destination;
}
