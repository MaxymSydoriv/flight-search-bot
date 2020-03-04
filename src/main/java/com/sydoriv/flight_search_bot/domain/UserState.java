package com.sydoriv.flight_search_bot.domain;

public enum UserState {
    DEFAULT("DEFAULT");

    private String stringName;

    private UserState(String stringName) {
        this.stringName = stringName;
    }

    public String getStringName() {
        return stringName;
    }
}
