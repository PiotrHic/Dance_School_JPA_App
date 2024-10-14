package com.example.dance_school_jpa_app.services.dancer;

import com.example.dance_school_jpa_app.domain.Dancer;

import java.util.List;

public interface DancerService {


    Dancer createDancer(Dancer dancer);

    Dancer getDancer (Integer id);
    List<Dancer> getAllDancers();

    Dancer updateDancer (Integer id, Dancer dancer);

    String deleteDancer (Integer id);
    void deleteAllDancers ();
}
