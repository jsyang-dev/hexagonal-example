package com.example.hexagonal.context.order.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void createOrder() {
        List<Map<String, Object>> items = List.of(
            Map.of("productName", "상품1", "quantity", 1),
            Map.of("productName", "상품2", "quantity", 2)
        );

        given()
            .contentType(ContentType.JSON)
            .body(items)
        .when()
            .post("/orders")
        .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("status", equalTo("CREATED"))
            .body("items", hasSize(2))
            .body("items[0].productName", equalTo("상품1"))
            .body("items[0].quantity", equalTo(1))
            .body("items[1].productName", equalTo("상품2"))
            .body("items[1].quantity", equalTo(2));
    }

    @Test
    void getOrder() {
        // given
        Long orderId = createSampleOrder();

        // when & then
        given()
        .when()
            .get("/orders/{id}", orderId)
        .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("status", equalTo("CREATED"))
            .body("items", hasSize(2));
    }

    @Test
    void getNonExistentOrder() {
        given()
        .when()
            .get("/orders/{id}", 999L)
        .then()
            .statusCode(404);
    }

    @Test
    void listOrders() {
        // given
        createSampleOrder();
        createSampleOrder();

        // when & then
        given()
        .when()
            .get("/orders")
        .then()
            .statusCode(200)
            .body("", hasSize(greaterThanOrEqualTo(2)))
            .body("[0].id", notNullValue())
            .body("[0].status", equalTo("CREATED"))
            .body("[0].items", hasSize(2));
    }

    @Test
    void updateOrder() {
        // given
        Long orderId = createSampleOrder();
        List<Map<String, Object>> updatedItems = List.of(
            Map.of("productName", "수정된상품1", "quantity", 3),
            Map.of("productName", "수정된상품2", "quantity", 4)
        );

        // when & then
        given()
            .contentType(ContentType.JSON)
            .body(updatedItems)
        .when()
            .put("/orders/{id}", orderId)
        .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("status", equalTo("CREATED"))
            .body("items", hasSize(2))
            .body("items[0].productName", equalTo("수정된상품1"))
            .body("items[0].quantity", equalTo(3))
            .body("items[1].productName", equalTo("수정된상품2"))
            .body("items[1].quantity", equalTo(4));
    }

    @Test
    void updateNonExistentOrder() {
        List<Map<String, Object>> items = List.of(
            Map.of("productName", "상품1", "quantity", 1)
        );

        given()
            .contentType(ContentType.JSON)
            .body(items)
        .when()
            .put("/orders/{id}", 999L)
        .then()
            .statusCode(404);
    }

    private Long createSampleOrder() {
        List<Map<String, Object>> items = List.of(
            Map.of("productName", "상품1", "quantity", 1),
            Map.of("productName", "상품2", "quantity", 2)
        );

        Response response = given()
            .contentType(ContentType.JSON)
            .body(items)
        .when()
            .post("/orders")
        .then()
            .statusCode(200)
            .extract().response();

        Number id = response.path("id");
        return id.longValue();
    }
} 