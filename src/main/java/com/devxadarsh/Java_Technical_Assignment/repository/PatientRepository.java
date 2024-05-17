package com.devxadarsh.Java_Technical_Assignment.repository;

import com.devxadarsh.Java_Technical_Assignment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByContactPhone(String contactPhone);
}
