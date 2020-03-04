package com.sydoriv.flight_search_bot.domain.lufthansa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LufthansaAuthResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiration;
}
