package com.devxadarsh.Java_Technical_Assignment;

import com.devxadarsh.Java_Technical_Assignment.entity.Doctor;
import com.devxadarsh.Java_Technical_Assignment.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JavaTechnicalAssignmentApplication implements CommandLineRunner {

	private final DoctorRepository doctorRepository;

    public JavaTechnicalAssignmentApplication(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(JavaTechnicalAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Doctor> doctors = List.of(
				new Doctor(1L,"Adarsh","Noida", "ex11@gmail.com","9123456781","Orthopedic",2667723),
				new Doctor(2L,"Prashant","Noida", "ex12@gmail.com","9123456782","Dermatology",2667723),
				new Doctor(3L,"Shivam","Noida", "ex13@gmail.com","9123456783","Gynecology",2667723),
				new Doctor(4L,"Shaurabh","Noida", "ex14@gmail.com","9123456784","ENT specialist",2667723),
				new Doctor(5L,"Krishna","Delhi", "ex21@gmail.com","9223456781","Orthopedic",2643623),
				new Doctor(6L,"Nidhi","Delhi", "ex22@gmail.com","9223456782","Dermatology",2643623),
				new Doctor(7L,"Shalini","Delhi", "ex23@gmail.com","9323456783","Gynecology",2643623),
				new Doctor(8L,"Anamika","Delhi", "ex24@gmail.com","9423456784","ENT specialist",2643623),
				new Doctor(9L,"Saumya","Faridabad", "ex31@gmail.com","9323456781","Orthopedic",2654623),
				new Doctor(10L,"Vivek","Faridabad", "ex32@gmail.com","9323456782","Dermatology",2654623),
				new Doctor(11L,"Pathak","Faridabad", "ex33@gmail.com","9323456783","Gynecology",2654623),
				new Doctor(12L,"Dinesh","Faridabad", "ex34@gmail.com","9323456784","ENT specialist",2654623)
		);
		doctorRepository.saveAll(doctors);
	}
}
