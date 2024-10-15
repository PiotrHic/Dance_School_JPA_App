package com.example.dance_school_jpa_app.controller;


import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.dto.DanceInstructorDTO;
import com.example.dance_school_jpa_app.dto.DancerDTO;
import com.example.dance_school_jpa_app.mappers.DanceInstructorMapper;
import com.example.dance_school_jpa_app.services.danceInstructor.DanceInstructorService;
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
@RequestMapping("/api/danceInstructors")
public class DanceInstructorController {

    private final DanceInstructorService danceInstructorService;

    private final DanceInstructorMapper danceInstructorMapper;

    @Operation(summary = "It adds a new Dance Instructor to the database")
    @ApiResponse(responseCode = "201",
            description = "Add new Dance Instructor to the database",
            content = {@Content(mediaType =  "application/json")})
    @PostMapping
    ResponseEntity<DanceInstructorDTO> createDanceInstructor(@RequestBody DanceInstructorDTO danceInstructorDTO){
        DanceInstructor toSave = danceInstructorService
                .createDanceInstructor(danceInstructorMapper.danceInstructorDTOToDanceInstructor(danceInstructorDTO));

        return new ResponseEntity<>(danceInstructorMapper.danceInstructorToDanceInstructorDTO(toSave),
                HttpStatus.valueOf(201));
    }


    @Operation(summary = "It updates Dance Instructor with the new data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update Dance Instructor to the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Dance Instructor was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @PutMapping("/{danceInstructorID}")
    ResponseEntity <DanceInstructorDTO> updateDanceInstructorById(@PathVariable("danceInstructorID") Integer danceInstructorID
            , @RequestBody DanceInstructorDTO danceInstructorDTO) {
        DanceInstructor updated = danceInstructorService.updateDanceInstructor(danceInstructorID,
                danceInstructorMapper.danceInstructorDTOToDanceInstructor(danceInstructorDTO));
        return new ResponseEntity<>(danceInstructorMapper.danceInstructorToDanceInstructorDTO(updated), HttpStatus.OK);
    }

    @Operation(summary = "It brings one Dance Instructor from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get one Dance Instructor from the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Dance Instructor was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @GetMapping("/{danceInstructorID}")
    ResponseEntity <DanceInstructorDTO> getDanceInstructorById(@PathVariable("danceInstructorID") Integer danceInstructorID) {
        DanceInstructor founded = danceInstructorService.getDanceInstructor(danceInstructorID);
        return new ResponseEntity<>(danceInstructorMapper.danceInstructorToDanceInstructorDTO(founded), HttpStatus.OK);
    }

    @Operation(summary = "Takes all Dance Instructors from the database")
    @ApiResponse(responseCode = "200",
            description = "Gives all Dance Instructors from the database",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping
    ResponseEntity <List<DanceInstructorDTO>> getAllDanceInstructor(){
        List<DanceInstructorDTO> dancerInstructorDTOs = danceInstructorService
                .getAllDanceInstructors()
                .stream()
                .map(danceInstructorMapper::danceInstructorToDanceInstructorDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dancerInstructorDTOs, HttpStatus.OK);
    }


    @Operation(summary = "It deletes one Dance Instructor from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Deletes one Dance Instructor from the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Dance Instructor was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @DeleteMapping("/{danceInstructorID}")
    ResponseEntity <String> deleteDanceInstructorById(@PathVariable("danceInstructorID") Integer danceInstructorID){
        String deleted = danceInstructorService.deleteDanceInstructor(danceInstructorID);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @Operation(summary = "Deletes all Dance Instructors from the database")
    @ApiResponse(responseCode = "200",
            description = "Deletes all Dance Instructors from the database",
            content = {@Content(mediaType =  "application/json")})
    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        danceInstructorService.deleteAllDanceInstructors();
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }
}
