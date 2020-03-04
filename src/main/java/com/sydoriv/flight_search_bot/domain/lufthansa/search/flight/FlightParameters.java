package com.sydoriv.flight_search_bot.domain.lufthansa.search.flight;

import lombok.Data;

@Data
public class FlightParameters {

    private Long sequenceNumber;
    private String origin;
    private String destination;
    private String serviceType;
    private String aircraftOwner;
    private String aircraftType;
    private String aircraftConfigurationVersion;
    private String registration;
    private Boolean op;
    private Long aircraftDepartureTimeUTC;
    private Long aircraftDepartureTimeDateDiffUTC;
    private Long aircraftDepartureTimeLT;
    private Long aircraftDepartureTimeDateDiffLT;
    private Long aircraftDepartureTimeVariation;
    private Long aircraftArrivalTimeUTC;
    private Long aircraftArrivalTimeDateDiffUTC;
    private Long aircraftArrivalTimeLT;
    private Long aircraftArrivalTimeDateDiffLT;
    private Long aircraftArrivalTimeVariation;
}
