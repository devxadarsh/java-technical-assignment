package com.devxadarsh.Java_Technical_Assignment.dto;

import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import com.devxadarsh.Java_Technical_Assignment.enums.Symptom;
import lombok.Data;

import java.util.List;

@Data
public class SuggestedDoctorDto {

    private String info;
    private Symptom symptom;
    private List<Doctor> suggested_doctors;
}
