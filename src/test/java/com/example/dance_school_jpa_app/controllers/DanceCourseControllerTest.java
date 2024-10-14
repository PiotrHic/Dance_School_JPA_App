package com.example.dance_school_jpa_app.controllers;

import com.example.dance_school_jpa_app.controller.DanceInstructorController;
import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.repositories.DanceCourseRepository;
import com.example.dance_school_jpa_app.repositories.DanceInstructorRepository;
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
public class DanceCourseControllerTest {

    @Autowired
    DanceCourseRepository danceCourseRepository;

    @Autowired
    DanceCourseController danceCourseController;

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

    private final String API = "/api/danceCourses";


    DanceCourse first = DanceCourse.builder()
            .name("test1")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test")
            .lastModifiedBy("test")
            .build();

    DanceCourse second = DanceCourse.builder()
            .name("test2")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test2")
            .lastModifiedBy("test2")
            .build();

    @Disabled
    @Test
    void shouldSaveOneDanceCourse() {

        danceCourseRepository.deleteAll();

        String requestBody = "{\n" +
                "  \"name\": \"test1\",\n" +
                "  \"createdAt\": \"2019-03-28 14:47:33 PM\", \n" +
                "  \"lastModifiedAt\": \"2019-03-28 14:47:33 PM\", \n" +
                "  \"createdBy\": \"test2\", \n" +
                "  \"lastModifiedBy\": \"test2\"" +
                "  \"FK1bdlrvdx9cb2s10puxn0syto3\": \"1\", \n}";

        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post(API)
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("test1", response.jsonPath().getString("name"));
        Assertions.assertEquals("2019-03-28 14:47:33 PM", response.jsonPath().getString("createdAt"));
        Assertions.assertEquals("2019-03-28 14:47:33 PM", response.jsonPath().getString("lastModifiedAt"));
        Assertions.assertEquals("test2", response.jsonPath().getString("createdBy"));
        Assertions.assertEquals("test2", response.jsonPath().getString("lastModifiedBy"));
        Assertions.assertEquals("1", response.jsonPath().getString("FK1bdlrvdx9cb2s10puxn0syto3"));
    }

    @Disabled
    @Test
    void updateOneDanceCourse(){
        danceCourseRepository.deleteAll();

        danceCourseRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("[test1]", response.jsonPath().getString("name"));
        ;

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
                .put(API + id)
                .then()
                .extract().response();


        System.out.println(response1.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response1.statusCode());
        Assertions.assertEquals("Put", response1.jsonPath().getString("name"));

        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
        Assertions.assertEquals("[Put]", response2.jsonPath().getString("name"));

    }

    @Disabled
    @Test
    void shouldFindOneDanceCourse(){
        danceCourseRepository.deleteAll();
        DanceCourse saved = danceCourseRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API + "/" + saved.getId())
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("test1", response.jsonPath().getString("name"));
    }

    @Test
    void NotFoundDanceCourse(){
        danceCourseRepository.deleteAll();
        DanceCourse saved = danceCourseRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API + "/" + (saved.getId() +10))
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Not Found", response.jsonPath().getString("error"));
    }

    @Disabled
    @Test
    void shouldFindTwoDanceCourses(){
        danceCourseRepository.deleteAll();
        danceCourseRepository.save(first);
        danceCourseRepository.save(second);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("[test1, test2]", response.jsonPath().getString("name"));
    }

    @Disabled
    @DisplayName("DeleteById Test")
    @Test
    void shouldDeleteOneDanceCourse(){
        danceCourseRepository.deleteAll();
        DanceCourse saved = danceCourseRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete(API + "/" + saved.getId())
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response.statusCode());


        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
        Assertions.assertEquals("[]", response2.jsonPath().getString("name"));
    }

    @Disabled
    @Test
    void shouldDeleteTwoDanceCourses(){
        danceCourseRepository.deleteAll();
        danceCourseRepository.save(first);
        danceCourseRepository.save(second);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());

        Response response1 = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/dancerInstructors")
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response1.statusCode());

        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancerInstructors")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
    }

    @Disabled
    @Test
    void UpdateWithBadData(){
        danceCourseRepository.deleteAll();
        DanceCourse saved = danceCourseRepository.save(first);

        String badId = String.valueOf(saved.getId() +1000000);

        String requestBody = "{\n" +
                "  \"id\": \"" + badId + "\",\n" +
                "  \"name\": \"Bad data\",\n}";


        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .put("/api/dancerInstructors/" + badId)
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Not Found", response.jsonPath().getString("error"));
    }
}
