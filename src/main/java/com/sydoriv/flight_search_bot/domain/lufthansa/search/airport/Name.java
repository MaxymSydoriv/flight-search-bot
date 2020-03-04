package com.sydoriv.flight_search_bot.domain.lufthansa.search.airport;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Name {

    @JsonProperty("@LanguageCode")
    private String languageCode;
    @JsonProperty("$")
    private String name;

}
