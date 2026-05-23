package com.ironhack.medicineproject.medicines;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository <MedicineModel,Long> {
}
