package com.ironhack.medicineproject.patients;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository <PatientModel,Long> {
}
