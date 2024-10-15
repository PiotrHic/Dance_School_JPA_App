package com.example.dance_school_jpa_app.controller;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.dto.DanceCourseDTO;
import com.example.dance_school_jpa_app.dto.DanceInstructorDTO;
import com.example.dance_school_jpa_app.mappers.DanceCourseMapper;
import com.example.dance_school_jpa_app.services.danceCourse.DanceCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/danceCourses")
public class DanceCourseController {

    private final DanceCourseService danceCourseService;

    private final DanceCourseMapper danceCourseMapper;

    private final String API_PATH_ID = "/{danceCourseID}";
    private final String ID_PATH = "danceCourseID";


    @Operation(summary = "It adds a new Dance Course to the database")
    @ApiResponse(responseCode = "201",
            description = "Add new Dance Course to the database",
            content = {@Content(mediaType =  "application/json")})
    @PostMapping
    ResponseEntity<DanceCourseDTO> createDanceCourse(@RequestBody DanceCourseDTO danceCourseDTO){
        DanceCourse toSave = danceCourseService
                .createDanceCourse(danceCourseMapper.danceCourseDTOToDanceCourse(danceCourseDTO));

        return new ResponseEntity<>(danceCourseMapper.danceCourseToDanceCourseDTO(toSave),
                HttpStatus.valueOf(201));
    }

    @Operation(summary = "It updates Dance Course with the new data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update Dance Course to the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Dance Course was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @PutMapping(API_PATH_ID)
    ResponseEntity <DanceCourseDTO> updateDanceCourseById(@PathVariable(ID_PATH) Integer danceCourseID
            , @RequestBody DanceCourseDTO danceCourseDTO) {
        DanceCourse updated = danceCourseService.updateDanceCourse(danceCourseID,
                danceCourseMapper.danceCourseDTOToDanceCourse(danceCourseDTO));
        return new ResponseEntity<>(danceCourseMapper.danceCourseToDanceCourseDTO(updated), HttpStatus.OK);
    }

    @Operation(summary = "It brings one Dance Course from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get one Dance Course from the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Dance Course was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @GetMapping(API_PATH_ID)
    ResponseEntity <DanceCourseDTO> getDanceCourseById(@PathVariable(ID_PATH) Integer danceCourseID) {
        DanceCourse founded = danceCourseService.getDanceCourse(danceCourseID);
        return new ResponseEntity<>(danceCourseMapper.danceCourseToDanceCourseDTO(founded), HttpStatus.OK);
    }

    @Operation(summary = "Takes all Dance Courses from the database")
    @ApiResponse(responseCode = "200",
            description = "Gives all Dance Courses from the database",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping
    ResponseEntity <List<DanceCourseDTO>> getAllDanceCourses(){
        List<DanceCourseDTO> dancerCourseDTOs = danceCourseService
                .getAllDanceCourses()
                .stream()
                .map(danceCourseMapper::danceCourseToDanceCourseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dancerCourseDTOs, HttpStatus.OK);
    }

    @Operation(summary = "It deletes one Dance Course from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Deletes one Dance Course from the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Dance Course was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @DeleteMapping(API_PATH_ID)
    ResponseEntity <String> deleteDanceCourseById(@PathVariable(ID_PATH) Integer danceCourseID){
        String deleted = danceCourseService.deleteDanceCourse(danceCourseID);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @Operation(summary = "Deletes all Dance Courses from the database")
    @ApiResponse(responseCode = "200",
            description = "Deletes all Dance Courses from the database",
            content = {@Content(mediaType =  "application/json")})

    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        danceCourseService.deleteAllDanceCourses();
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }


}
