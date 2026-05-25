package com.ironhack.medicineproject.repository;

import com.ironhack.medicineproject.model.PrescriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository <PrescriptionModel, Long> {



}
