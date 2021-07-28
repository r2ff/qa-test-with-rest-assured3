
import models.ResourceData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReqresTests {

    @Test
    void getListResourceWithSpecsTest() {

        given()
                .spec(Specs.request)
                .when()
                .get("/unknown")
                .then()
                .spec(Specs.response)
                .log().body()
                .body("total", is(12));

    }

    @Test
    void singleResourcesWithLombokModel() {

        ResourceData resp = given()
                .spec(Specs.request)
                .when()
                .get("/unknown/2")
                .then()
                .spec(Specs.response)
                .log().body()
                .extract().as(ResourceData.class);


        assertEquals("17-2031", resp.getResource().getPantoneValue());

    }

    @Test
    void getListResoursesWithLombokAndGroovy() {

        given()
                .spec(Specs.request)
                .when()
                .get("/unknown")
                .then()
                .spec(Specs.response)
                .log().body()
                .body("data.findAll{it.year > 2001}.year",
                        hasItem(2005));

    }
}
