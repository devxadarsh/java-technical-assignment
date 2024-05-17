package com.devxadarsh.Java_Technical_Assignment.service.impl;

import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import com.devxadarsh.Java_Technical_Assignment.entity.Patient;
import com.devxadarsh.Java_Technical_Assignment.enums.Symptom;
import com.devxadarsh.Java_Technical_Assignment.exception.DataAlreadyExistsException;
import com.devxadarsh.Java_Technical_Assignment.mapper.SymptomToSecialityMapper;
import com.devxadarsh.Java_Technical_Assignment.repository.DoctorRepository;
import com.devxadarsh.Java_Technical_Assignment.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class implements the DoctorService interface and provides the business logic
 * for managing doctor-related operations.
 */

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    /**
     * Creates a new doctor in the system.
     *
     * @param request The Doctor object containing details of the new doctor.
     * @return The created Doctor object.
     * @throws DataAlreadyExistsException if a doctor with the same email or contact phone already exists.
     */
    @Override
    public Doctor createDoctor(Doctor request) {
        if (doctorRepository.existsByEmail(request.getEmail())) {
            throw new DataAlreadyExistsException("Email " + request.getEmail() + " already linked with other Doctor.");
        } else if (doctorRepository.existsByContactPhone(request.getContactPhone())) {
            throw new DataAlreadyExistsException("Phone No " + request.getContactPhone() + " already linked with other Doctor.");
        }
        request.setJoinedOn(Instant.now().toEpochMilli());
        return doctorRepository.save(request);
    }

    /**
     * Retrieves all doctors in the system.
     *
     * @return A list of all doctors.
     */
    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    /**
     * Deletes a doctor by their ID.
     *
     * @param id The ID of the doctor to delete.
     * @return True if the doctor was deleted successfully, otherwise false.
     */
    @Override
    public boolean deleteById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            doctorRepository.delete(doctor);
            return true;
        } else return false;
    }

    /**
     * Finds a doctor by contact phone.
     *
     * @param phone The contact phone of the doctor to retrieve.
     * @return The doctor with the specified contact phone.
     */
    @Override
    public Doctor findDoctorByPhoneNo(String phone) {
        ;
        return doctorRepository.findByContactPhone(phone);
    }

    /**
     * Finds doctors by city.
     *
     * @param city The city in which to search for doctors.
     * @return A list of doctors located in the specified city.
     */
    @Override
    public List<Doctor> findDoctorsByCity(String city) {
        return doctorRepository.findByCity(city);
    }

    /**
     * Updates a doctor by ID.
     *
     * @param updatedDoctor The updated information of the doctor.
     * @param id            The ID of the doctor to update.
     * @return The updated doctor if found and updated successfully, otherwise null.
     */
    @Override
    public Doctor updatePatientById(Doctor updatedDoctor, Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            doctor.setName(updatedDoctor.getName());
            doctor.setCity(updatedDoctor.getCity());
            doctor.setEmail(updatedDoctor.getEmail());
            doctor.setContactPhone(updatedDoctor.getContactPhone());
            doctor.setSpeciality(updatedDoctor.getSpeciality());
            return doctorRepository.save(doctor);
        }
        return null;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    /**
     * Suggests doctors based on the specified symptom.
     *
     * @param symptom The symptom for which doctors are suggested.
     * @return A list of doctors specializing in treating the specified symptom.
     */
    @Override
    public List<Doctor> suggestDoctorBySymptom(Symptom symptom) {
        SymptomToSecialityMapper symptomToSecialityMapper = new SymptomToSecialityMapper();

        String speciality = symptomToSecialityMapper.symptomToSpecialityMap.get(symptom);

        if (speciality != null) {
            return doctorRepository.findBySpeciality(speciality);
        } else {
            return Collections.emptyList();
        }
    }

}