package com.example.dance_school_jpa_app.controllers;

import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.domain.Dancer;
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
public class DanceInstructorControllerIssue {

    @Autowired
    DanceInstructorRepository danceInstructorRepository;

    @Autowired
    DanceInstructorController danceInstructorController;

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
    DanceInstructor first = DanceInstructor.builder()
            .name("test1")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test")
            .lastModifiedBy("test")
            .build();

    DanceInstructor second = DanceInstructor.builder()
            .name("test2")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .createdBy("test2")
            .lastModifiedBy("test2")
            .build();

    @Disabled
    @Test
    void shouldSaveOneDanceInstructor() {

        danceInstructorRepository.deleteAll();

        String requestBody = "{\n" +
                "  \"name\": \"test1\",\n" +
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
                .post("/api/danceInstructors")
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
    void updateOneDanceInstructor(){
        danceInstructorRepository.deleteAll();

        danceInstructorRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/danceInstructors")
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

    @Disabled
    @Test
    void shouldFindOneDanceInstructor(){
        danceInstructorRepository.deleteAll();
        DanceInstructor saved = danceInstructorRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/danceInstructors/" + saved.getId())
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("test1", response.jsonPath().getString("name"));
    }

    @Disabled
    @Test
    void NotFoundDanceInstructor(){
        danceInstructorRepository.deleteAll();
        DanceInstructor saved = danceInstructorRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancerInstructors/" + (saved.getId() +10))
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Not Found", response.jsonPath().getString("error"));
    }

    @Disabled
    @Test
    void shouldFindTwoDanceInstructors(){
        danceInstructorRepository.deleteAll();
        danceInstructorRepository.save(first);
        danceInstructorRepository.save(second);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancerInstructors")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("[test1, test2]", response.jsonPath().getString("name"));
    }

    @Disabled
    @DisplayName("Delete By Id Test")
    @Test
    void shouldDeleteOneDanceInstructor(){
        danceInstructorRepository.deleteAll();
        DanceInstructor saved = danceInstructorRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/dancerInstructors/" + saved.getId())
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response.statusCode());


        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
        Assertions.assertEquals("[]", response2.jsonPath().getString("name"));
    }

    @Test
    void shouldDeleteButNotFound(){
        danceInstructorRepository.deleteAll();
        DanceInstructor saved = danceInstructorRepository.save(first);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/dancerInstructors/" + (saved.getId() + 10))
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Not Found", response.jsonPath().getString("error"));
    }

    @Test
    void shouldDeleteTwoDancers(){
        danceInstructorRepository.deleteAll();
        danceInstructorRepository.save(first);
        danceInstructorRepository.save(second);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancerInstructors/")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());

        Response response1 = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/dancerInstructors/")
                .then()
                .extract().response();

        System.out.println(response.jsonPath().prettyPrint());

        Assertions.assertEquals(200, response1.statusCode());

        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/dancerInstructors/")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());
    }

    @Disabled
    @Test
    void UpdateWithBadData(){
        danceInstructorRepository.deleteAll();
        DanceInstructor saved = danceInstructorRepository.save(first);

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
