package com.devxadarsh.Java_Technical_Assignment.service;

import com.devxadarsh.Java_Technical_Assignment.dto.PatientDto;
import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import com.devxadarsh.Java_Technical_Assignment.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(Patient request);

    List<Patient> findAll();

    Patient getPatient(Patient request);

    boolean deleteById(Long id);

    Patient findPatientById(Long id);

    Patient updatePatientById(Patient updatedPatient, Long id);
}
