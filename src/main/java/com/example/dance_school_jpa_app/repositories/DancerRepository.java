package com.example.dance_school_jpa_app.repositories;

import com.example.dance_school_jpa_app.domain.Dancer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DancerRepository extends JpaRepository<Dancer, Integer> {
}
