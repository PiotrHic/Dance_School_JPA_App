package com.example.dance_school_jpa_app.controllers;

import com.example.dance_school_jpa_app.controller.DancerController;
import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.repositories.DancerRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DancerControllerTest {

    @Autowired
    DancerRepository dancerRepository;

    @Autowired
    DancerController dancerController;

    @LocalServerPort
    private Integer port;

    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(
            "mysql:9.0.1"
    );

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer .stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @BeforeEach
    void setup(){
        RestAssured.baseURI = "http://localhost:" + port;
    }

    Dancer first = Dancer.builder()
            .name("test1")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test")
            .lastModifiedBy("test")
            .build();

    Dancer second = Dancer.builder()
            .name("test2")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test2")
            .lastModifiedBy("test2")
            .build();

    @Test
    void testAdd(){
        dancerRepository.save(first);
        dancerRepository.save(second);
    }
    @Test
    void shouldSaveOneDancer() {

        dancerRepository.deleteAll();

        String requestBody = "{\n" +
                "  \"name\": \"Michał\",\n" +
                "  \"createdAt\": \"2019-03-28 14:47:33 PM\", \n" +
                "  \"lastModifiedAt\": \"2019-03-28 14:47:33 PM\", \n" +
                "  \"createdBy\": \"test2\", \n" +
                "  \"lastModifiedBy\": \"test2\", \n" +
                "  \"FK1bdlrvdx9cb2s10puxn0syto3\": \"1\", \n}";

        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/api/dancers")
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("Michał", response.jsonPath().getString("name"));
        Assertions.assertEquals("2019-03-28 14:47:33 PM", response.jsonPath().getString("createdAt"));
        Assertions.assertEquals("2019-03-28 14:47:33 PM", response.jsonPath().getString("lastModifiedAt"));
        Assertions.assertEquals("test2", response.jsonPath().getString("createdBy"));
        Assertions.assertEquals("test2", response.jsonPath().getString("lastModifiedBy"));
        Assertions.assertEquals("1", response.jsonPath().getString("FK1bdlrvdx9cb2s10puxn0syto3"));
    }

    @Test
    void updateOneDancer(){
        dancerRepository.deleteAll();

        dancerRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("[Michał]", response.jsonPath().getString("name"));
        Assertions.assertEquals("[BACHATA]", response.jsonPath().getString("danceStyle"));

        String id = first.getId().toString();

        String requestBody = "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"name\": \"Put\",\n}";

        System.out.println(requestBody.toString());

        Response response1 = given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .put("/api/dancers/" + id)
                .then()
                .extract().response();


        System.out.println(response1.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response1.statusCode());
        Assertions.assertEquals("Put", response1.jsonPath().getString("name"));

        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
        Assertions.assertEquals("[Put]", response2.jsonPath().getString("name"));

    }

    @Test
    void shouldFindOneUser(){
        dancerRepository.deleteAll();
        Dancer saved = dancerRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers/" + saved.getId())
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Michał", response.jsonPath().getString("name"));
    }

    @Test
    void NotFoundUser(){
        dancerRepository.deleteAll();
        Dancer saved = dancerRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers/" + (saved.getId() +10))
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Not Found", response.jsonPath().getString("error"));
    }

    @Test
    void shouldFindTwoUsers(){
        dancerRepository.deleteAll();
        dancerRepository.save(first);
        dancerRepository.save(second);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("[Michał, Robert]", response.jsonPath().getString("name"));
        Assertions.assertEquals("[BACHATA, Samba]", response.jsonPath().getString("danceStyle"));
    }




    @DisplayName("Delete By Id Test")
    @Test
    void shouldDeleteOneUser(){
        dancerRepository.deleteAll();
        Dancer saved = dancerRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/dancers/" + saved.getId())
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Michał", response.jsonPath().getString("name"));
        Assertions.assertEquals("BACHATA", response.jsonPath().getString("danceStyle"));

        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
        Assertions.assertEquals("[]", response2.jsonPath().getString("name"));
        Assertions.assertEquals("[]", response2.jsonPath().getString("danceStyle"));
    }

    @Test
    void shouldDeleteButNotFound(){
        dancerRepository.deleteAll();
        Dancer saved = dancerRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/dancers/" + (saved.getId() + 10))
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Not Found", response.jsonPath().getString("error"));

    }

    @Test
    void shouldDeleteTwoUsers(){
        dancerRepository.deleteAll();
        dancerRepository.save(first);
        dancerRepository.save(second);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("[Michał, Roman]", response.jsonPath().getString("name"));
        Assertions.assertEquals("[BACHATA, RUMBA]", response.jsonPath().getString("danceStyle"));

        Response response1 = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/dancers")
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response1.statusCode());

        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
        Assertions.assertEquals("[]", response2.jsonPath().getString("name"));
        Assertions.assertEquals("[]", response2.jsonPath().getString("danceStyle"));
    }

    @Test
    void UpdateWithBadData(){
        dancerRepository.deleteAll();
        Dancer saved = dancerRepository.save(first);

        String badId = String.valueOf(saved.getId() +1000000);

        String requestBody = "{\n" +
                "  \"id\": \"" + badId + "\",\n" +
                "  \"name\": \"Jan\",\n" +
                "  \"danceStyle\": \"Walc\" \n}";


        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .put("/api/dancers/" + badId)
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Not Found", response.jsonPath().getString("error"));
    }
}

