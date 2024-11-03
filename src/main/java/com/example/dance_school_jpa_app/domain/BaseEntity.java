package com.example.dance_school_jpa_app.domain;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {

    @Column(
            updatable = false,
            nullable = false)
    private LocalDateTime createdAt;
    @Column(
            nullable = false
    )
    private LocalDateTime lastModifiedAt;
    @Column(
            updatable = false,
            nullable = false)
    private String createdBy;
    private String lastModifiedBy;
}
