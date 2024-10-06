package com.example.dance_school_jpa_app.services;

import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.exception.DancerNotFoundException;
import com.example.dance_school_jpa_app.repositories.DancerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DancerServiceImpl implements DancerService {

    private final DancerRepository dancerRepository;
    @Override
    public Dancer createDancer(Dancer dancer) {
        return dancerRepository.save(dancer);
    }

    @Override
    public Dancer getDancer(Integer id) {
        if(!dancerRepository.existsById(id)){
            throw new DancerNotFoundException("Dancer with id: " + id + " was not found!");
        }
        return dancerRepository.getReferenceById(id);
    }


    @Override
    public List<Dancer> getAllDancers(){
        return dancerRepository.findAll();
    }
    @Override
    public Dancer updateDancer(Integer id, Dancer dancer) {
        if(!dancerRepository.existsById(id)){
            throw new DancerNotFoundException("Dancer with id: " + id + " was not found!");
        }
        Dancer toUpdate = dancerRepository.getReferenceById(id);
        toUpdate.setName(dancer.getName());
        toUpdate.setName(dancer.getName());
        toUpdate.setDanceCourses(dancer.getDanceCourses());
        toUpdate.setCreatedAt(dancer.getCreatedAt());
        toUpdate.setCreatedBy(dancer.getCreatedBy());
        toUpdate.setLastModifiedAt(dancer.getLastModifiedAt());
        toUpdate.setLastModifiedBy(dancer.getLastModifiedBy());
        return dancerRepository.save(toUpdate);
    }

    @Override
    public Dancer deleteDancer(Integer id) {
        if(!dancerRepository.existsById(id)){
            throw new DancerNotFoundException("Dancer with id: " + id + " was not found!");
        }
        Dancer deleted = dancerRepository.getReferenceById(id);
        dancerRepository.deleteById(id);
        return deleted;
    }

    @Override
    public void deleteAllDancers() {
        dancerRepository.deleteAll();
    }
}
