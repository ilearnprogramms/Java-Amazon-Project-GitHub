package com.ironhack.medicineproject.repository;

import com.ironhack.medicineproject.model.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository <DoctorModel, Long> {

    DoctorModel findByUsernameIgnoreCase(String username);

}
