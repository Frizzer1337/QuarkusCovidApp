package com.frizzer.graal.controller;

import com.frizzer.graal.entity.CountryDataResponse;
import com.frizzer.graal.entity.CountryResponse;
import com.frizzer.graal.service.CovidService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
public class CovidController {

  @Inject
  CovidService service;

  @GET
  @Path("/countries")
  @Produces(MediaType.APPLICATION_JSON)
  public List<CountryResponse> hello() {
    return service.findCountry();
  }

  @GET
  @Path("/by-country/{country}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<CountryDataResponse> findCountryData(@PathParam("country") String country,
      @QueryParam("from") String start, @QueryParam("to") String end) {
    return service.findCountryData(country, start, end);
  }


}
