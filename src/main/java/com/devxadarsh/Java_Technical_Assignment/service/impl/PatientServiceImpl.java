package com.devxadarsh.Java_Technical_Assignment.service.impl;

import com.devxadarsh.Java_Technical_Assignment.dto.PatientDto;
import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import com.devxadarsh.Java_Technical_Assignment.entity.Patient;
import com.devxadarsh.Java_Technical_Assignment.exception.DataAlreadyExistsException;
import com.devxadarsh.Java_Technical_Assignment.exception.DataNotFoundException;
import com.devxadarsh.Java_Technical_Assignment.mapper.PatientMapper;
import com.devxadarsh.Java_Technical_Assignment.repository.PatientRepository;
import com.devxadarsh.Java_Technical_Assignment.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * This class implements the PatientService interface, providing functionalities to manage Patient entities.
 * It includes methods for creating, finding, and deleting patients.
 */

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    /**
     * Creates a new patient.
     *
     * @param request The Patient object containing information about the new patient.
     * @return The created Patient object.
     * @throws DataAlreadyExistsException If a patient with the same email or contact phone already exists.
     */
    @Override
    public Patient createPatient(Patient request) {
        if (patientRepository.existsByEmail(request.getEmail())) {
            throw new DataAlreadyExistsException("Phone No " + request.getContactPhone() + " already linked with other Patient.");
        }else if (patientRepository.existsByContactPhone(request.getContactPhone())) {
            throw new DataAlreadyExistsException("Phone No " + request.getContactPhone() + " already linked with other Doctor.");
        }
        request.setJoinedOn(Instant.now().toEpochMilli());
        return patientRepository.save(request);
    }

    /**
     * Retrieves all patients.
     *
     * @return A list of all patients.
     */
    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    /**
     * Deletes a patient by ID.
     *
     * @param id The ID of the patient to be deleted.
     * @return True if the patient was successfully deleted, otherwise false.
     */
    @Override
    public boolean deleteById(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            patientRepository.delete(patient);
            return true;
        } else return false;
    }

    /**
     * Finds a patient by ID.
     *
     * @param id The ID of the patient to retrieve.
     * @return The patient with the specified ID.
     * @throws DataNotFoundException If no patient with the specified ID is found.
     */
    @Override
    public Patient findPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient != null){
            return patient;
        }
        throw new DataNotFoundException("Patient with id [" + id + "] Not Found");
    }

    /**
     * Updates a patient by ID.
     *
     * @param updatedPatient The updated information of the patient.
     * @param id             The ID of the patient to update.
     * @return The updated patient if found and updated successfully, otherwise null.
     */
    @Override
    public Patient updatePatientById(Patient updatedPatient, Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient != null){
            patient.setName(updatedPatient.getName());
            patient.setCity(updatedPatient.getCity());
            patient.setEmail(updatedPatient.getEmail());
            patient.setContactPhone(updatedPatient.getContactPhone());
            patient.setSymptom(updatedPatient.getSymptom());
            return patientRepository.save(patient);
        }
        return null;
    }

    /**
     * Retrieves a patient by email.
     *
     * @param request The Patient object containing the email to search for.
     * @return The patient with the specified email.
     */
    @Override
    public Patient getPatient(Patient request) {
        return patientRepository.findByEmail(request.getEmail());
    }
}
