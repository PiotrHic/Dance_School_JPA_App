package com.example.dance_school_jpa_app;

import com.example.dance_school_jpa_app.domain.DanceCourse;
import com.example.dance_school_jpa_app.domain.Dancer;
import com.example.dance_school_jpa_app.repositories.DanceCourseRepository;
import com.example.dance_school_jpa_app.repositories.DancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class DanceSchoolJpaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanceSchoolJpaAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
                DancerRepository dancerRepository,
                DanceCourseRepository danceCourseRepository
    ) {
        return args -> {
            System.out.println("Hello from Bootstrap!");

            Dancer dancer = Dancer.builder()
                    .name("test3")
                    .createdAt(LocalDateTime.now())
                    .lastModifiedAt(LocalDateTime.now())
                    .createdBy("test1")
                    .lastModifiedBy("test1")
                    .build();

            DanceCourse dance_course = DanceCourse.builder()
                    .name("test3")
                    .createdAt(LocalDateTime.now())
                    .lastModifiedAt(LocalDateTime.now())
                    .createdBy("test1")
                    .lastModifiedBy("test1")
                    .build();

            dancerRepository.save(dancer);
            danceCourseRepository.save(dance_course);
        };
    }
}
