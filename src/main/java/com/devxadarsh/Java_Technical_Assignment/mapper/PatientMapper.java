package com.devxadarsh.Java_Technical_Assignment.mapper;

import com.devxadarsh.Java_Technical_Assignment.dto.PatientDto;
import com.devxadarsh.Java_Technical_Assignment.dto.SuggestedDoctorDto;
import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import com.devxadarsh.Java_Technical_Assignment.entity.Patient;

import java.util.List;

/**
 * A mapper class responsible for converting Patient entities to PatientDto objects
 * with additional information about associated doctors and registration status.
 */
public class PatientMapper {

    public static PatientDto mapToOldDto(Patient patient){
        PatientDto patientDto = new PatientDto();

        patientDto.setInfo("Patient data updated successfully");

        // Map various attributes from the Patient entity to the PatientDto
        patientDto.setName(patient.getName());
        patientDto.setEmail(patient.getEmail());
        patientDto.setContactPhone(patient.getContactPhone());
        patientDto.setCity(patient.getCity());
        patientDto.setSymptom(patient.getSymptom());
        patientDto.setJoinedOn(patient.getJoinedOn());

        // Return the populated PatientDto object
        return patientDto;
    }

    public static SuggestedDoctorDto suggestedDoctorDto(Patient patient, List<Doctor> doctors, boolean registered){
        SuggestedDoctorDto suggestedDoctorDto = new SuggestedDoctorDto();

        // Set appropriate info message based on registration status
        if(registered){
            suggestedDoctorDto.setInfo("This Patient is already registered.");
        }else suggestedDoctorDto.setInfo("Patient Registered Successfully");

        suggestedDoctorDto.setSymptom(patient.getSymptom());

        // Set the list of suggested doctors for the patient
        suggestedDoctorDto.setSuggested_doctors(doctors);

        // Return the populated PatientDto object
        return suggestedDoctorDto;
    }
}
