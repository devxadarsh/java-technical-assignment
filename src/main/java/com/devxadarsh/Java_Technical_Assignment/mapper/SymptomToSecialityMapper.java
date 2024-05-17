package com.devxadarsh.Java_Technical_Assignment.mapper;

import com.devxadarsh.Java_Technical_Assignment.enums.Symptom;

import java.util.Map;

public class SymptomToSecialityMapper {

    public Map<Symptom, String> symptomToSpecialityMap = Map.of(
            Symptom.ARTHRITIS, "Orthopedic",
            Symptom.BACK_PAIN, "Orthopedic",
            Symptom.TISSUE_INJURIES, "Orthopedic",
            Symptom.DYSMENORRHEA, "Gynecology",
            Symptom.SKIN_INFECTION, "Dermatology",
            Symptom.SKIN_BURN, "Dermatology",
            Symptom.EAR_PAIN, "ENT specialist"
    );
}
