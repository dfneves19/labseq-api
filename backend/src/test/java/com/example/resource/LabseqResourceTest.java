package com.example.resource;

import com.example.service.LabseqService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import static io.restassured.RestAssured.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class LabseqResourceTest {

    @InjectMock
    LabseqService service;

    @Test
    public void testNumber() {
        when(service.getSequence(25)).thenReturn(new BigInteger("59"));

        given()
                .pathParam("n", 25)
                .when().get("/labseq/{n}")
                .then()
                    .statusCode(200)
                    .body("value", is("59"));
    }

    @Test
    void testNegativeNumber() {
        given()
                .pathParam("n", -1)
                .when().get("/labseq/{n}")
                .then()
                    .statusCode(400)
                    .body(is("n must be a positive integer"));
    }

    @Test
    void testNonNumber() {
        given()
                .pathParam("n", "abc")
                .when().get("/labseq/{n}")
                .then()
                .statusCode(400)
                .body(is("n must be a positive integer"));
    }
}
