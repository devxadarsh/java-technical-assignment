package com.devxadarsh.Java_Technical_Assignment.controller;

import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import com.devxadarsh.Java_Technical_Assignment.exception.DataNotFoundException;
import com.devxadarsh.Java_Technical_Assignment.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    /**
     * Creates a new doctor.
     *
     * @param request the details of the doctor to be created
     * @return a response entity indicating the success or failure of the creation operation
     */
    @PostMapping("/register")
    public ResponseEntity<String> createDoctor(@RequestBody Doctor request) {
        Doctor doctor = doctorService.createDoctor(request);
        if(doctor != null){
            return new ResponseEntity<>("Doctor Registered Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Doctor Not Register", HttpStatus.BAD_REQUEST);
    }

    /**
     * Retrieves all doctors.
     *
     * @return a response entity containing a list of all doctors
     */
    @GetMapping("/all_doctor")
    public ResponseEntity<List<Doctor>> findAll(){
        return ResponseEntity.ok(doctorService.findAll());
    }

    /**
     * Finds a doctor by their phone number.
     *
     * @param phone the phone number of the doctor to find
     * @return a response entity containing the details of the found doctor
     */
    @GetMapping("/find")
    public ResponseEntity<Doctor> findDoctorByPhoneNo(@RequestParam String phone){
        Doctor doctor = doctorService.findDoctorByPhoneNo(phone);
        if (doctor != null){
            return ResponseEntity.ok(doctor);
        }
        throw new RuntimeException("This contact is not attached with any Doctor");
    }

    /**
     * Finds a doctor by their phone number.
     *
     * @param id the phone number of the doctor to find
     * @return a response entity containing the details of the found doctor
     */
    @GetMapping("/details")
    public ResponseEntity<Doctor> findDoctorById(@RequestParam Long id){
        Doctor doctor = doctorService.findDoctorById(id);
        if (doctor != null){
            return ResponseEntity.ok(doctor);
        }
        throw new DataNotFoundException("This contact is not attached with any Doctor");
    }

    /**
     * Removes a doctor by their ID.
     *
     * @param id the ID of the doctor to remove
     * @return a response entity indicating the success or failure of the removal operation
     */
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeDoctorById(@RequestParam Long id) {
        boolean isRemoved = doctorService.deleteById(id);
        if(isRemoved){
            return new ResponseEntity<>("Doctor removed Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Doctor Not found",HttpStatus.NOT_FOUND);
    }


    /**
     * Updates a doctor's information.
     *
     * @param updatedDoctor The updated information of the doctor.
     * @param id            The ID of the doctor to update.
     * @return A response entity indicating the result of the update operation.
     */
    @PutMapping("/update")
    public ResponseEntity<String> updateDoctor(@RequestBody Doctor updatedDoctor, @RequestParam Long id){
        Doctor doctor = doctorService.updatePatientById(updatedDoctor,id);
        if(doctor != null){
            return ResponseEntity.ok("Patient updated successfully");
        }
        throw new DataNotFoundException("Patient Id [" + id + "] not found");
    }

}
