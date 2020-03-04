package com.sydoriv.flight_search_bot.domain.lufthansa.search.flight;

import java.util.List;
import lombok.Data;

@Data
public class FlightSearchResponseEntity {

    private String airline;
    private long flightNumber;
    private String suffix;
    private PeriodOfOperation periodOfOperationUTC;
    private PeriodOfOperation periodOfOperationLT;
    private List<FlightParameters> legs;


}
