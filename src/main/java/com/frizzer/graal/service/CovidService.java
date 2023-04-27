package com.frizzer.graal.service;

import com.frizzer.graal.client.CountryClient;
import com.frizzer.graal.entity.CountryDataRequest;
import com.frizzer.graal.entity.CountryDataResponse;
import com.frizzer.graal.entity.CountryRequest;
import com.frizzer.graal.entity.CountryResponse;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Singleton
public class CovidService {

  @RestClient
  CountryClient client;

  public List<CountryResponse> findCountry() {
    return toCountryResponse(client.findCountry());
  }

  public List<CountryDataResponse> findCountryData(String countryName,String start,String end) {
    return toCountryDataResponse(client.findCountryData(countryName,start,end));
  }

  private List<CountryDataResponse> toCountryDataResponse(List<CountryDataRequest> list) {
    List<CountryDataResponse> result = new ArrayList<>();
    for (int i = 0; i < list.size() - 1; i++) {
      CountryDataRequest current = list.get(i);
      CountryDataRequest next = list.get(i + 1);
      int todayInfected = next.confirmed() - current.confirmed();
      result.add(
          new CountryDataResponse(current.id(), current.country(), current.confirmed(), todayInfected, current.date()));
    }
    return result;
  }

  private List<CountryResponse> toCountryResponse(List<CountryRequest> list) {
    List<CountryResponse> result = new ArrayList<>();
    for (var country : list) {
      result.add(new CountryResponse(country.country(), country.slug(), country.iso2()));
    }
    return result;
  }

}
