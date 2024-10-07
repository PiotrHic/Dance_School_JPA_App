package com.example.dance_school_jpa_app.services.danceInstructor;

import com.example.dance_school_jpa_app.domain.DanceInstructor;
import com.example.dance_school_jpa_app.exception.JPAEntityNotFoundException;
import com.example.dance_school_jpa_app.repositories.DanceInstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DanceInstructorServiceImpl implements DanceInstructorService {

    private final DanceInstructorRepository danceInstructorRepository;
    @Override
    public DanceInstructor createDanceInstructor(DanceInstructor danceInstructor) {
        return danceInstructorRepository.save(danceInstructor);
    }

    @Override
    public DanceInstructor getDanceInstructor(Integer id) {
        if(!danceInstructorRepository.existsById(id)){
            throw new JPAEntityNotFoundException("Dance Instructor with id: " + id + " was not found!");
        }
        return danceInstructorRepository.getReferenceById(id);
    }

    @Override
    public List<DanceInstructor> getAllDanceInstructors() {
        return danceInstructorRepository.findAll();
    }

    @Override
    public DanceInstructor updateDanceInstructor(Integer id, DanceInstructor danceInstructor) {
        if(!danceInstructorRepository.existsById(id)){
            throw new JPAEntityNotFoundException("Dance Instructor with id: " + id + " was not found!");
        }
        DanceInstructor toUpdate = danceInstructorRepository.getReferenceById(id);
        toUpdate.setName(danceInstructor.getName());
        toUpdate.setName(danceInstructor.getName());
        toUpdate.setDanceCourse(danceInstructor.getDanceCourse());
        toUpdate.setCreatedAt(danceInstructor.getCreatedAt());
        toUpdate.setCreatedBy(danceInstructor.getCreatedBy());
        toUpdate.setLastModifiedAt(danceInstructor.getLastModifiedAt());
        toUpdate.setLastModifiedBy(danceInstructor.getLastModifiedBy());
        return danceInstructorRepository.save(toUpdate);
    }

    @Override
    public DanceInstructor deleteDanceInstructor(Integer id) {
        if(!danceInstructorRepository.existsById(id)){
            throw new JPAEntityNotFoundException("Dance Instructor with id: " + id + " was not found!");
        }
        DanceInstructor deleted = danceInstructorRepository.getReferenceById(id);
        danceInstructorRepository.deleteById(id);
        return deleted;
    }

    @Override
    public void deleteAllDanceInstructors() {
        danceInstructorRepository.deleteAll();
    }
}
