package com.ironhack.medicineproject.service;

import com.ironhack.medicineproject.dto.DoctorDTO;
import com.ironhack.medicineproject.dto.MedicineDTO;
import com.ironhack.medicineproject.dto.PatientDTO;
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

public class PrescriptionService {

    @Autowired
    PrescriptionRepository prescriptionRepository;
    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    public GlobalStatus addPrescription(PrescriptionDTO prescriptionDTO, DoctorDTO doctorDTO, PatientDTO patientDTO, MedicineDTO medicineDTO) {

        if(prescriptionDTO.getDescription().isEmpty())
            return GlobalStatus.PRESCRIPTION_CAN_NOT_BE_EMPTY;

        DoctorModel doctor = doctorRepository
                .findByDrLastName(doctorDTO.getDrLastName())
                .orElseThrow();

        PatientModel patient = patientRepository
                .findByPatientLastNameIgnoreCase(patientDTO.getPatientLastName())
                .orElseThrow();

        MedicineModel medicine = medicineRepository
                .findByMedicineName(medicineDTO.getMedicineName())
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


}
