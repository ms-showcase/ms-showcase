package io.population;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class PopulationResourceTest {

    @Test
    public void populationSvkEndpoint() {
        given()
                .when().get("/population/SVK")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("countryCode", equalTo("SVK"))
                .body("country", equalTo("Slovak Republic"))
                .body("population", equalTo(5428704));
    }

    @Test
    public void populationListEndpoint() {
        given()
                .when().get("/population")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("size()", is(20))
                .body("country", hasItems("Argentina", "Australia"))
                .body("countryCode", hasItems("ARG", "AUS"));
    }

}