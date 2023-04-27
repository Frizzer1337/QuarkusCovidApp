package com.frizzer.graal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CountryDataRequest(
    @JsonProperty("ID")
    String id,
    @JsonProperty("Country")
    String country,
    @JsonProperty("Province")
    String province,
    @JsonProperty("Confirmed")
    int confirmed,
    @JsonProperty("Date")
    String date
) {

}
