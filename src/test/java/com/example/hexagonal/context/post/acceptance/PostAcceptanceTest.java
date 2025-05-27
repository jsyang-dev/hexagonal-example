package com.example.hexagonal.context.post.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.context.annotation.Import;

import java.util.Map;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestKafkaConfig.class)
class PostAcceptanceTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("게시글 등록 성공")
    void registerPost_success() {
        Map<String, Object> post = Map.of(
                "title", "테스트 제목",
                "content", "테스트 내용",
                "author", "홍길동"
        );

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(post)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .body(is(emptyOrNullString()));
    }

    @Test
    @DisplayName("게시글 단건 조회 성공")
    void getPost_success() {
        Map<String, Object> post = Map.of(
                "title", "조회 제목",
                "content", "조회 내용",
                "author", "이몽룡"
        );
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(post)
        .when()
                .post("/posts")
        .then()
                .statusCode(201);

        // 등록된 게시글 중 가장 마지막 게시글을 조회
        Long id = RestAssured.given()
        .when()
                .get("/posts")
        .then()
                .statusCode(200)
                .extract().jsonPath().getList("id", Long.class)
                .stream().reduce((first, second) -> second).orElseThrow();

        RestAssured.given()
        .when()
                .get("/posts/" + id)
        .then()
                .statusCode(200)
                .body("title", equalTo("조회 제목"))
                .body("content", equalTo("조회 내용"))
                .body("author", equalTo("이몽룡"));
    }

    @Test
    @DisplayName("존재하지 않는 게시글 조회시 404 반환")
    void getPost_notFound() {
        RestAssured.given()
        .when()
                .get("/posts/99999")
        .then()
                .statusCode(404);
    }
}
