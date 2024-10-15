package com.example.dance_school_jpa_app.controller;

import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.dto.DancerDTO;
import com.example.dance_school_jpa_app.mappers.DancerMapper;
import com.example.dance_school_jpa_app.services.dancer.DancerService;
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
@RequestMapping("/api/dancers")
public class DancerController {

    private final DancerService dancerService;

    private final DancerMapper dancerMapper;


    @Operation(summary = "It adds a new Dancer to the database")
    @ApiResponse(responseCode = "201",
            description = "Add new Dancer to the database",
            content = {@Content(mediaType =  "application/json")})
    @PostMapping
    ResponseEntity<DancerDTO> createDancer(@RequestBody DancerDTO dancerDTO){
        Dancer toSave = dancerService.createDancer(dancerMapper.dancerDTOToDancer(dancerDTO));

        return new ResponseEntity<>(dancerMapper.dancerToDancerDTO(toSave), HttpStatus.valueOf(201));
    }

    @Operation(summary = "It updates Dancer with the new data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Update Dancer to the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Dancer was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @PutMapping("/{dancerID}")
    ResponseEntity <DancerDTO> updateDancerById(@PathVariable("dancerID") Integer dancerID, @RequestBody DancerDTO dancerDTO) {
        Dancer updated = dancerService.updateDancer(dancerID, dancerMapper.dancerDTOToDancer(dancerDTO));
        return new ResponseEntity<>(dancerMapper.dancerToDancerDTO(updated), HttpStatus.OK);
    }

    @Operation(summary = "It brings one Dancer from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Get one Dancer from the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Dancer was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @GetMapping("/{dancerID}")
    ResponseEntity <DancerDTO> getDancerById(@PathVariable("dancerID") Integer dancerID) {
        Dancer founded = dancerService.getDancer(dancerID);
        return new ResponseEntity<>(dancerMapper.dancerToDancerDTO(founded), HttpStatus.OK);
    }

    @Operation(summary = "Takes all Dancers from the database")
    @ApiResponse(responseCode = "200",
            description = "Gives all Dancers from the database",
            content = {@Content(mediaType =  "application/json")})
    @GetMapping
    ResponseEntity <List<DancerDTO>> getAllDancers(){
        List<DancerDTO> dancerDTOs = dancerService
                .getAllDancers()
                .stream()
                .map(dancerMapper::dancerToDancerDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dancerDTOs, HttpStatus.OK);
    }

    @Operation(summary = "It deletes one Dancer from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Deletes one dancer from the database",
                    content = {@Content(mediaType =  "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Dancer was not found in database",
                    content = {@Content(mediaType =  "application/json")}),
    })
    @DeleteMapping("/{dancerID}")
    ResponseEntity <String> deleteDancerById(@PathVariable("dancerID") Integer dancerID){
        String deleted = dancerService.deleteDancer(dancerID);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @Operation(summary = "Deletes all Dancers from the database")
    @ApiResponse(responseCode = "200",
            description = "Deletes all Dancers from the database",
            content = {@Content(mediaType =  "application/json")})
    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        dancerService.deleteAllDancers();
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }
}
