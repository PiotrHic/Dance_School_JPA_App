package com.example.dance_school_jpa_app.controllers;

import com.example.dance_school_jpa_app.controller.DancerController;
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

    @Test
    void shouldSaveOneDancer() {

        dancerRepository.deleteAll();

        String requestBody = "{\n" +
                "  \"name\": \"Michał\",\n" +
                "  \"danceStyle\": \"BACHATA\" \n}";

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
        Assertions.assertEquals("BACHATA", response.jsonPath().getString("danceStyle"));
    }

    @Test
    void updateOneDancer(){
        dancerRepository.deleteAll();
        Dancer createDancer = new Dancer("Michał", "BACHATA");

        dancerRepository.save(createDancer);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("[Michał]", response.jsonPath().getString("name"));
        Assertions.assertEquals("[BACHATA]", response.jsonPath().getString("danceStyle"));

        String id = createDancer.getId().toString();

        String requestBody = "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"name\": \"Jan\",\n" +
                "  \"danceStyle\": \"Walc\" \n}";

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
        Assertions.assertEquals("Jan", response1.jsonPath().getString("name"));
        Assertions.assertEquals("Walc", response1.jsonPath().getString("danceStyle"));

        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
        Assertions.assertEquals("[Jan]", response2.jsonPath().getString("name"));
        Assertions.assertEquals("[Walc]", response2.jsonPath().getString("danceStyle"));
    }

    @Test
    void shouldFindOneUser(){
        dancerRepository.deleteAll();
        Dancer saved = dancerRepository.save(new Dancer("Michał", "BACHATA"));

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers/" + saved.getId())
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Michał", response.jsonPath().getString("name"));
        Assertions.assertEquals("BACHATA", response.jsonPath().getString("danceStyle"));
    }

    @Test
    void NotFoundUser(){
        dancerRepository.deleteAll();
        Dancer saved = dancerRepository.save(new Dancer("John", "Samba"));

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
        dancerRepository.save(new Dancer("Michał", "BACHATA"));
        dancerRepository.save(new Dancer("Robert", "Samba"));

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
        Dancer saved = dancerRepository.save(new Dancer("Michał", "BACHATA"));

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
        Dancer saved = dancerRepository.save(new Dancer("Michał", "BACHATA"));

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
        dancerRepository.save(new Dancer("Michał", "BACHATA"));
        dancerRepository.save(new Dancer("Roman", "RUMBA"));

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
        Dancer saved = dancerRepository.save(new Dancer("John", "Samba"));

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

