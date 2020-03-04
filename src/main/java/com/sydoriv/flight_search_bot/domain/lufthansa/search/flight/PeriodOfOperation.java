package com.sydoriv.flight_search_bot.domain.lufthansa.search.flight;

import lombok.Data;

@Data
public class PeriodOfOperation {

    private String startDate;
    private String endDate;
    private Long daysOfOperation;
}
