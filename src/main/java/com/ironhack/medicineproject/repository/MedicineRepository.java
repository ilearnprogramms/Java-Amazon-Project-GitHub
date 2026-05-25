package com.ironhack.medicineproject.repository;

import com.ironhack.medicineproject.model.MedicineModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository <MedicineModel,Long> {

    Optional<MedicineModel> findByMedicineName (String medicineName);
    MedicineModel deleteByMedicineName(String medicineName);
}
