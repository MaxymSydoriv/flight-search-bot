package com.sydoriv.flight_search_bot.domain.lufthansa.search.flight;

import lombok.Data;

@Data
public class DataElement {

    private Integer startLegSequenceNumber;
    private Integer endLegSequenceNumber;
    private Long id;
    private String value;

}
