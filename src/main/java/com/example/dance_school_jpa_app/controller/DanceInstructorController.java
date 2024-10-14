package com.example.dance_school_jpa_app.controller;


import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.domain.DancerDTO;
import com.example.dance_school_jpa_app.services.danceInstructor.DanceInstructorService;
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

    @PostMapping
    ResponseEntity<DanceInstructorDTO> createDancer(@RequestBody DancerDTO dancerDTO){
        DanceInstructor toSave = danceInstructorService
                .createDanceInstructor(danceInstructorMapper.danceInstructorDTOTodanceInstructor(dancerDTO));

        return new ResponseEntity<>(danceInstructorMapper.danceInstructorTodanceInstructorDTO(toSave),
                HttpStatus.valueOf(201));
    }

    @PutMapping("/{danceInstructorID}")
    ResponseEntity <DanceInstructorDTO> updateDancerById(@PathVariable("danceInstructorID") Integer danceInstructorID
            , @RequestBody DancerDTO dancerDTO) {
        DanceInstructor updated = danceInstructorService.updateDanceInstructor(danceInstructorID,
                danceInstructorMapper.danceInstructorDTOTodanceInstructor(dancerDTO));
        return new ResponseEntity<>(danceInstructorMapper.danceInstructorTodanceInstructorDTO(updated), HttpStatus.OK);
    }


    @GetMapping("/{danceInstructorID}")
    ResponseEntity <DanceInstructorDTO> getDancerById(@PathVariable("danceInstructorID") Integer danceInstructorID) {
        DanceInstructor founded = danceInstructorService.getDanceInstructor(danceInstructorID);
        return new ResponseEntity<>(danceInstructorMapper.danceInstructorTodanceInstructorDTO(founded), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity <List<DanceInstructorDTO>> getAllDancers(){
        List<DanceInstructorDTO> dancersDTO = danceInstructorService
                .getAllDanceInstructors()
                .stream()
                .map(danceInstructorMapper::danceInstructorTodanceInstructorDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dancersDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{danceInstructorID}")
    ResponseEntity <String> deleteDancerById(@PathVariable("danceInstructorID") Integer danceInstructorID){
        String deleted = danceInstructorService.deleteDanceInstructor(danceInstructorID);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        danceInstructorService.deleteAllDanceInstructors();
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }
}
