package com.ironhack.medicineproject.doctors;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository <DoctorModel, Long> {

    List<DoctorModel> findByDrLastNameIgnoreCase(String drLastName);
    List<DoctorModel> findByUsernameIgnoreCase(String username);
}
