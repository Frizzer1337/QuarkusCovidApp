package com.frizzer.graal.entity;

public record CountryDataResponse(
    String id,
    String country,
    int confirmedTotal,
    int confirmedToday,
    String date
) {}
