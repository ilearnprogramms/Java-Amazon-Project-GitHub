package com.ironhack.medicineproject.repository;

import com.ironhack.medicineproject.model.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository <DoctorModel, Long> {

    DoctorModel findByUsernameIgnoreCase(String username);
}
