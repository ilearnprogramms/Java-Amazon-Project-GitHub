package com.ironhack.medicineproject.service;

import com.ironhack.medicineproject.dto.PrescriptionDTO;
import com.ironhack.medicineproject.enums.GlobalStatus;
import com.ironhack.medicineproject.model.DoctorModel;
import com.ironhack.medicineproject.model.MedicineModel;
import com.ironhack.medicineproject.model.PatientModel;
import com.ironhack.medicineproject.model.PrescriptionModel;
import com.ironhack.medicineproject.repository.DoctorRepository;
import com.ironhack.medicineproject.repository.MedicineRepository;
import com.ironhack.medicineproject.repository.PatientRepository;
import com.ironhack.medicineproject.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepository prescriptionRepository;
    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    public List<PrescriptionModel> getAllPrescriptions(){
        return  prescriptionRepository.findAll();
    }

    public List<PrescriptionModel> getByPatientID(Long patientID) {

        return prescriptionRepository.findByPatient_PatientID(patientID);
    }

    public GlobalStatus addPrescription(PrescriptionDTO prescriptionDTO) {

        if(prescriptionDTO.getDescription().isEmpty())
            return GlobalStatus.PRESCRIPTION_CAN_NOT_BE_EMPTY;

        DoctorModel doctor = doctorRepository
                .findById(prescriptionDTO.getDoctorID())
                .orElseThrow();

        PatientModel patient = patientRepository
                .findById(prescriptionDTO.getPatientID())
                .orElseThrow();

        MedicineModel medicine = medicineRepository
                .findByMedicineID(prescriptionDTO.getMedicineID())
                .orElseThrow();

        PrescriptionModel prescription = new PrescriptionModel(
                prescriptionDTO.getDescription(),
                prescriptionDTO.getPrescribedDate(),
                patient,
                doctor,
                medicine
        );
            prescriptionRepository.save(prescription);

        return GlobalStatus.PRESCRIPTION_CREATED;
    }

    public GlobalStatus deletePrescription(PrescriptionDTO prescriptionDTO) {

        final List<PrescriptionModel> findByPrescriptionID
                = Collections.singletonList(prescriptionRepository
                .findByPrescriptionID(prescriptionDTO.getPrescriptionID()));

        if (findByPrescriptionID.isEmpty())
            return GlobalStatus.PRESCRIPTION_NOT_FOUND;
        PrescriptionModel prescription = findByPrescriptionID.get(0);
        prescriptionRepository.delete(prescription);

        return GlobalStatus.PRESCRIPTION_DELETED;
    }

}
