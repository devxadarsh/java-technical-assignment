package com.devxadarsh.Java_Technical_Assignment.repository;

import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByEmail(String email);

    List<Doctor> findBySpeciality(String speciality);

    List<Doctor> findBySpecialityAndCity(String speciality, String patientCity);

    boolean existsByContactPhone(String contactPhone);

    Doctor findByContactPhone(String phone);

    List<Doctor> findByCity(String city);
}
