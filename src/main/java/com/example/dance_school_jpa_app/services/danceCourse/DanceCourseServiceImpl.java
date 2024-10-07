package com.example.dance_school_jpa_app.services.danceCourse;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.exception.DanceCourseNotFoundException;
import com.example.dance_school_jpa_app.exception.DancerNotFoundException;
import com.example.dance_school_jpa_app.repositories.DanceCourseRepository;
import com.example.dance_school_jpa_app.services.danceCourse.DanceCourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DanceCourseServiceImpl implements DanceCourseService {

    private final DanceCourseRepository danceCourseRepository;
    @Override
    public DanceCourse createDanceCourse(DanceCourse danceCourse) {
        return danceCourseRepository.save(danceCourse);
    }

    @Override
    public DanceCourse getDanceCourse(Integer id) {
        if(!danceCourseRepository.existsById(id)){
            throw new DanceCourseNotFoundException("Dance Course with id: " + id + " was not found!");
        }
        return danceCourseRepository.getReferenceById(id);
    }

    @Override
    public List<DanceCourse> getAllDanceCourses() {
        return danceCourseRepository.findAll();
    }

    @Override
    public DanceCourse updateDanceCourse(Integer id, DanceCourse danceCourse) {
        if(!danceCourseRepository.existsById(id)){
            throw new DancerNotFoundException("Dancer with id: " + id + " was not found!");
        }
        DanceCourse toUpdate = danceCourseRepository.getReferenceById(id);
        toUpdate.setName(danceCourse.getName());
        toUpdate.setName(danceCourse.getName());
        toUpdate.setDanceInstructors(danceCourse.getDanceInstructors());
        toUpdate.setCreatedAt(danceCourse.getCreatedAt());
        toUpdate.setCreatedBy(danceCourse.getCreatedBy());
        toUpdate.setLastModifiedAt(danceCourse.getLastModifiedAt());
        toUpdate.setLastModifiedBy(danceCourse.getLastModifiedBy());
        return danceCourseRepository.save(toUpdate);
    }

    @Override
    public DanceCourse deleteDanceCourse(Integer id) {
        if(!danceCourseRepository.existsById(id)){
            throw new DanceCourseNotFoundException("Dance Course with id: " + id + " was not found!");
        }
        DanceCourse deleted = danceCourseRepository.getReferenceById(id);
        danceCourseRepository.deleteById(id);
        return deleted;
    }

    @Override
    public void deleteAllDanceCourses() {
        danceCourseRepository.deleteAll();
    }
}
