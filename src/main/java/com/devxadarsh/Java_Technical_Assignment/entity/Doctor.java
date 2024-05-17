package com.devxadarsh.Java_Technical_Assignment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "CenterName is required")
    @Size(min = 3, max = 40, message = "CenterName must be less than 40 characters")
    @Column(length = 40, nullable = false)
    private String name;

    @NotBlank(message = "CenterName is required")
    @Size(max = 20, message = "CenterName must be less than 40 characters")
    @Column(length = 40, nullable = false)
    // I can also use enum but, it will be simple to implement here, so I will implement the enum method in Patient Entity
    @Pattern(regexp = "^(Delhi|Noida|Faridabad)$", message = "City must be Delhi, Noida, or Faridabad")
    private String city;

    @Column(length = 40)
    @Email(message = "Invalid email format")
    private String email;

    @Column(length = 15, nullable = false)
    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number. Please check it again")
    private String contactPhone;

    @Column(length = 15, nullable = false)
    @Pattern(regexp = "^(Orthopedic|Gynecology|Dermatology|ENT specialist)$", message = "Specialty must be Orthopedic, Gynecology, Dermatology, or ENT specialist")
    private String speciality;

    @Column(nullable = false)
    private long joinedOn;


}
