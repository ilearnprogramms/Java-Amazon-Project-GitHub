package com.ironhack.medicineproject.repository;

import com.ironhack.medicineproject.model.MedicineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository <MedicineModel,Long> {

    Optional<MedicineModel> findByMedicineID (Long medicineID);
    MedicineModel findByMedicineName (String medicineName);
}
