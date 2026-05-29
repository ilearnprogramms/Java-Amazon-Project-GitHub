package com.ironhack.medicineproject.service;

import com.ironhack.medicineproject.dto.PrescriptionDTO;
import com.ironhack.medicineproject.exceptions.SearchNotFoundException;
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

    public PrescriptionModel addPrescription(PrescriptionDTO prescriptionDTO) {

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
        return prescriptionRepository.save(prescription);
    }

    public PrescriptionModel deletePrescription(PrescriptionDTO prescriptionDTO) {

        PrescriptionModel prescription = prescriptionRepository.findByPrescriptionID(
                        prescriptionDTO.getPrescriptionID());

        if (prescription == null) {
            throw new SearchNotFoundException("Couldn't find the prescription: " +
                      prescriptionDTO.getPrescriptionID());
        }
        prescriptionRepository.delete(prescription);
        return prescription;
    }

}
