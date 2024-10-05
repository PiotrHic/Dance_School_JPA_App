package com.example.dance_school_jpa_app;

import org.springframework.boot.SpringApplication;

public class TestDanceSchoolJpaAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(DanceSchoolJpaAppApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
