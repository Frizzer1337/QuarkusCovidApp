package com.frizzer.graal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.frizzer.graal.controller.CovidController;
import com.frizzer.graal.entity.CountryDataResponse;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(CovidController.class)
public class CovidControllerTest {

  private final String countriesEndpoint = "/countries";

  @Test
  public void testCountriesEndpoint() {
    given()
        .when().get(countriesEndpoint)
        .then()
        .statusCode(200)
        .body(not(empty()));
  }

  @Test
  public void testSingleCountryEndpoint() {
    given()
        .when().get(countriesEndpoint + "/belarus")
        .then()
        .statusCode(200)
        .body(not(empty()));
  }

  @Test
  public void testSingleCountryEndpointByListSize() {
    int daysBetween = 9;
    List<CountryDataResponse> response = List.of(given()
        .when().get(countriesEndpoint + "/belarus?from=2020-04-01T00:00:00Z&to=2020-04-10T00:00:00Z")
        .then()
        .statusCode(200)
        .body(not(empty()))
        .extract()
        .as(CountryDataResponse[].class));
    assertEquals(daysBetween, response.size());
  }

  @Test
  public void testSingleCountryEndpointByMaxListValue() {
    int maxToday = 495;
    List<CountryDataResponse> response = List.of(given()
        .when().get(countriesEndpoint + "/belarus?from=2020-04-01T00:00:00Z&to=2020-04-10T00:00:00Z")
        .then()
        .statusCode(200)
        .body(not(empty()))
        .extract()
        .as(CountryDataResponse[].class));
    assertEquals(maxToday, response.stream()
        .map(CountryDataResponse::confirmedToday)
        .sorted(Comparator.reverseOrder())
        .toList()
        .get(0));
  }

  @Test
  public void testSingleCountryEndpointByCountryWithProvince() {
    List<CountryDataResponse> response = List.of(given()
        .when().get(countriesEndpoint + "/uk?from=2020-04-01T00:00:00Z&to=2020-04-10T00:00:00Z")
        .then()
        .statusCode(200)
        .body(not(empty()))
        .extract()
        .as(CountryDataResponse[].class));
    assertTrue(response.stream().allMatch(it -> it.confirmedToday() > 0));
  }


}