package com.frizzer.graal.controller;

import com.frizzer.graal.service.CovidService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class CovidController {

  @Inject
  CovidService service;

  @GET
  @Path("/countries")
  @Produces(MediaType.APPLICATION_JSON)
  public Response hello() {
    return Response.ok(service.findCountry()).build();
  }

  @GET
  @Path("/countries/{country}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findCountryData(@PathParam("country") String country,
      @QueryParam("from") String start, @QueryParam("to") String end) {
    return Response.ok(service.findCountryData(country, start, end)).build();
  }


}
