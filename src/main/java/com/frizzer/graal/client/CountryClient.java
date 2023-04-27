package com.frizzer.graal.client;

import com.frizzer.graal.entity.CountryRequest;
import com.frizzer.graal.entity.CountryDataRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "/")
public interface CountryClient {

  @GET
  @Path("/countries")
  List<CountryRequest> findCountry();

  @GET
  @Path("/country/{countryName}")
  List<CountryDataRequest> findCountryData(@PathParam("countryName")String countryName,@QueryParam("from") String start,@QueryParam("to") String end);

}
