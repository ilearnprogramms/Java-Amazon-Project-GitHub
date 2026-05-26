package com.ironhack.medicineproject.repository;

import com.ironhack.medicineproject.model.PrescriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository <PrescriptionModel, Long> {

    List<PrescriptionModel> findByPatient_PatientID(Long patientID);

    Optional<PrescriptionModel> findByPrescriptionID(Long prescriptionID);


}