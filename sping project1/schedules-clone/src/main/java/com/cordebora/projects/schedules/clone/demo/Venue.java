package com.cordebora.projects.schedules.clone.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Venue {

    @JsonProperty("default")
    private String defaultVenue;

    public String getDefaultVenue() {
        return defaultVenue;
    }

    public void setDefaultVenue(String defaultVenue) {
        this.defaultVenue = defaultVenue;
    }
}