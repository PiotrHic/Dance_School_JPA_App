package com.example.dance_school_jpa_app.controller;


import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.dto.DanceInstructorDTO;
import com.example.dance_school_jpa_app.dto.DancerDTO;
import com.example.dance_school_jpa_app.mappers.DanceInstructorMapper;
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
    ResponseEntity<DanceInstructorDTO> createDanceInstructor(@RequestBody DanceInstructorDTO danceInstructorDTO){
        DanceInstructor toSave = danceInstructorService
                .createDanceInstructor(danceInstructorMapper.danceInstructorDTOToDanceInstructor(danceInstructorDTO));

        return new ResponseEntity<>(danceInstructorMapper.danceInstructorToDanceInstructorDTO(toSave),
                HttpStatus.valueOf(201));
    }

    @PutMapping("/{danceInstructorID}")
    ResponseEntity <DanceInstructorDTO> updateDanceInstructorById(@PathVariable("danceInstructorID") Integer danceInstructorID
            , @RequestBody DanceInstructorDTO danceInstructorDTO) {
        DanceInstructor updated = danceInstructorService.updateDanceInstructor(danceInstructorID,
                danceInstructorMapper.danceInstructorDTOToDanceInstructor(danceInstructorDTO));
        return new ResponseEntity<>(danceInstructorMapper.danceInstructorToDanceInstructorDTO(updated), HttpStatus.OK);
    }


    @GetMapping("/{danceInstructorID}")
    ResponseEntity <DanceInstructorDTO> getDanceInstructorById(@PathVariable("danceInstructorID") Integer danceInstructorID) {
        DanceInstructor founded = danceInstructorService.getDanceInstructor(danceInstructorID);
        return new ResponseEntity<>(danceInstructorMapper.danceInstructorToDanceInstructorDTO(founded), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity <List<DanceInstructorDTO>> getAllDanceInstructor(){
        List<DanceInstructorDTO> dancerInstructorDTOs = danceInstructorService
                .getAllDanceInstructors()
                .stream()
                .map(danceInstructorMapper::danceInstructorToDanceInstructorDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dancerInstructorDTOs, HttpStatus.OK);
    }


    @DeleteMapping("/{danceInstructorID}")
    ResponseEntity <String> deleteDanceInstructorById(@PathVariable("danceInstructorID") Integer danceInstructorID){
        String deleted = danceInstructorService.deleteDanceInstructor(danceInstructorID);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @DeleteMapping()
    ResponseEntity <String> deleteAllDancers(){
        danceInstructorService.deleteAllDanceInstructors();
        return new ResponseEntity<>("Database is empty", HttpStatus.OK);
    }
}
