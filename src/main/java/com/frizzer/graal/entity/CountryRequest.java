package com.frizzer.graal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


public record CountryRequest(
    @JsonProperty(value = "Country")
    String country,
    @JsonProperty(value = "Slug")
    String slug,
    @JsonProperty(value = "ISO2")
    String iso2) {
}
