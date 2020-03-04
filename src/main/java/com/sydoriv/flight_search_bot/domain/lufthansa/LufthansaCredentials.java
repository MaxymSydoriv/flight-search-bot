package com.sydoriv.flight_search_bot.domain.lufthansa;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class LufthansaCredentials {

    @Value("${lufthansa.api.auth.client_id}")
    private String client_id;
    @Value("${lufthansa.api.auth.secret}")
    private String client_secret;
    @Value("${lufthansa.api.auth.grant_type}")
    private String grant_type;
}
