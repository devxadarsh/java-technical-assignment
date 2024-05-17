package com.devxadarsh.Java_Technical_Assignment.dto;

import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import com.devxadarsh.Java_Technical_Assignment.enums.Symptom;
import lombok.Data;

import java.util.List;

@Data
public class PatientDto {

    // Additional information or notes about the patient
    private String info;

    // Basic patient details
    private String name;
    private String city;
    private String email;
    private String contactPhone;

    // Symptom experienced by the patient
    private Symptom symptom;

    // Timestamp when the patient joined or registered
    private long joinedOn;

    // List of doctors suggested for the patient's symptom
    private List<Doctor> suggested_doctors;

}
