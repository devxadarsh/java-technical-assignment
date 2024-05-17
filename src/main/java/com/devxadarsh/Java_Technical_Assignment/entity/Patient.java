package com.devxadarsh.Java_Technical_Assignment.entity;

import com.devxadarsh.Java_Technical_Assignment.enums.Symptom;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "CenterName is required")
    @Size(min = 3, max = 40, message = "CenterName must be less than 40 characters")
    @Column(length = 40, nullable = false)
    private String name;

    @NotBlank(message = "CenterName is required")
    @Size(min = 3, max = 40, message = "CenterName must be less than 40 characters")
    @Column(length = 40, nullable = false)
    private String city;

    @Column(length = 40)
    @Email(message = "Invalid email format")
    private String email;

    @Column(length = 15, nullable = false)
    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number. Please check it again")
    private String contactPhone;

    @Column(length = 15, nullable = false)
    private Symptom symptom;

    @Column(nullable = false)
    private long joinedOn;

}
