package com.devxadarsh.Java_Technical_Assignment.service;

import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import com.devxadarsh.Java_Technical_Assignment.entity.Patient;
import com.devxadarsh.Java_Technical_Assignment.enums.Symptom;

import java.util.List;

public interface DoctorService {

    Doctor createDoctor(Doctor request);

    List<Doctor> findAll();

    List<Doctor> suggestDoctorBySymptom(Symptom symptom);

    boolean deleteById(Long id);

    Doctor findDoctorByPhoneNo(String phone);

    List<Doctor> findDoctorsByCity(String city);

    Doctor updatePatientById(Doctor updatedDoctor, Long id);

    Doctor findDoctorById(Long id);
}
