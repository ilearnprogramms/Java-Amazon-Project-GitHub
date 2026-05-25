package com.ironhack.medicineproject.repository;

import com.ironhack.medicineproject.model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository <PatientModel,Long> {

    PatientModel findByUsernameIgnoreCase(String username);
    Optional<PatientModel> findByPatientLastNameIgnoreCase(String patientLastName);

}
