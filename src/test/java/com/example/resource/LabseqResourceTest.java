package com.example.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class LabseqResourceTest {
    @Test
    void testLabseq() {
        given()
            .pathParam("n", 29)
            .when()
                .get("/labseq/{n}")
            .then()
                .statusCode(200)
                .body("n", is(29))
                .body("value", is("129"));
    }

    @Test
    void testNonInteger() {
        given()
            .pathParam("n", "abc")
            .when()
                .get("/labseq/{n}")
            .then()
                .statusCode(400)
                .body(is("n must be a positive integer"));
    }

    @Test
    void testNegative() {
        given()
            .pathParam("n", -1)
            .when()
                .get("/labseq/{n}")
            .then()
                .statusCode(400)
                .body(is("n must be a positive integer"));
    }

    @Test
    void testLargeNPerformance() {
        int n = 100000;

        long start = System.nanoTime();

        given()
            .pathParam("n", n)
            .when()
                .get("/labseq/{n}")
            .then()
                .statusCode(200)
                .body("n", is(n));

        long end = System.nanoTime();
        long durationMillis = (end - start) / 1000000;

        assertTrue(durationMillis < 10000, "Calculation took too long");
    }
}
