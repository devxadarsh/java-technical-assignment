package com.devxadarsh.Java_Technical_Assignment.controller;

import com.devxadarsh.Java_Technical_Assignment.dto.SuggestedDoctorDto;
import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import com.devxadarsh.Java_Technical_Assignment.entity.Patient;
import com.devxadarsh.Java_Technical_Assignment.exception.DataNotFoundException;
import com.devxadarsh.Java_Technical_Assignment.list.CustomList;
import com.devxadarsh.Java_Technical_Assignment.mapper.PatientMapper;
import com.devxadarsh.Java_Technical_Assignment.service.DoctorService;
import com.devxadarsh.Java_Technical_Assignment.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final DoctorService doctorService;

    /**
     * Registers a patient.
     * If the patient already exists, it sets the registration status to true.
     * Otherwise, it creates a new patient and sets the registration status to false.
     *
     * @param request the patient details to register
     * @return the registered patient details along with a list of suggested doctors
     */
    @PostMapping("/register")
    public ResponseEntity<SuggestedDoctorDto> registerPatient(@RequestBody Patient request) {
        Patient patient = patientService.getPatient(request);
        boolean isRegistered = true;
        if (patient == null) {
            patientService.createPatient(request);
            isRegistered = false;
        }
        List<Doctor> doctors = data(request);
        return ResponseEntity.ok(PatientMapper.suggestedDoctorDto(request, doctors, isRegistered));
    }

    /**
     * Finds and returns all patients.
     *
     * @return a list of all patients
     */
    @GetMapping("/all_patient")
    public ResponseEntity<List<Patient>> findAll(){
        return ResponseEntity.ok(patientService.findAll());
    }

    /**
     * Finds a patient by their ID and return Suggested doctor in city.
     *
     * @param id the ID of the patient to find
     * @return the found patient details
     */
    @GetMapping("/sug_doc")
    public ResponseEntity<SuggestedDoctorDto> findPatientById(@RequestParam Long id){
        Patient patient = patientService.findPatientById(id);
        if(patient != null){
            List<Doctor> doctors = data(patient);
            return ResponseEntity.ok(PatientMapper.suggestedDoctorDto(patient, doctors, true));
        }
        throw new DataNotFoundException("Patient Id [" + id + "] not found");
    }

    /**
     * Finds a patient details by their ID.
     *
     * @param id the ID of the patient to find
     * @return the found patient details
     */
    @GetMapping("/details")
    public ResponseEntity<Patient> findPatientDetailsById(@RequestParam Long id){
        Patient patient = patientService.findPatientById(id);
        if(patient != null){
            return ResponseEntity.ok(patient);
        }
        throw new DataNotFoundException("Patient Id [" + id + "] not found");
    }

    /**
     * Finds a patient details by their ID.
     *
     * @param id the ID of the patient to find
     * @return the found patient details
     */
    @PutMapping("/update")
    public ResponseEntity<String> updatePatient(@RequestBody Patient updatedPatient, @RequestParam Long id){
        Patient patient = patientService.updatePatientById(updatedPatient,id);
        if(patient != null){
            return ResponseEntity.ok("Patient updated successfully");
        }
        throw new DataNotFoundException("Patient Id [" + id + "] not found");
    }

    /**
     * Removes a patient by their ID.
     * If the patient is successfully removed, returns a success message.
     * Otherwise, returns a message indicating the patient was not found.
     *
     * @param id the ID of the patient to remove
     * @return a response entity with the result of the removal operation
     */
    @DeleteMapping("/remove")
    public ResponseEntity<String> removePatientById(@RequestParam Long id) {

        boolean isRemoved = patientService.deleteById(id);
        if(isRemoved){
            return new ResponseEntity<>("Patient removed Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Patient Not found",HttpStatus.NOT_FOUND);
    }

    /**
     * Suggests doctors for a patient based on their symptoms and city.
     * If no doctors are found for the symptom in the specified city, throws an EntityNotFoundException.
     * If location is not available, throws an EntityNotFoundException.
     *
     * @param request the patient details containing symptoms and city
     * @return a list of suggested doctors
     */

    public List<Doctor> data(Patient request){
        CustomList customList = new CustomList();
        String city = request.getCity();
        List<Doctor> doctors = doctorService.suggestDoctorBySymptom(request.getSymptom()).stream()
                .filter(doctor -> doctor.getCity().equals(request.getCity())).toList();
        if (customList.cityList.contains(city)) {
            if (doctors.isEmpty()){
                throw new EntityNotFoundException("There isn't any doctor present at your location for your symptom");
            }
            return doctors;
        } else {
            throw new EntityNotFoundException("We are still waiting to expand to your location");
        }
    }

}
