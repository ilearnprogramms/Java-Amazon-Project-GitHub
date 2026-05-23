package com.ironhack.medicineproject.patients;

import com.ironhack.medicineproject.security.GlobalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientModel> getAllPatients() {
        return patientRepository.findAll();
    }

    public GlobalStatus addNewPatient(PatientDTO patientDTO) {

        if(patientDTO.getPatientLastName().length() < 3)
            return GlobalStatus.NAME_TOO_SHORT;

        if (patientDTO.getPatientLastName().length() > 19)
            return GlobalStatus.NAME_TOO_LONG;

        PatientModel patient = new PatientModel();
            patient.setPatientTitle(patientDTO.getPatientTitle());
            patient.setPatientLastName(patientDTO.getPatientLastName());
            patient.setUsername(patientDTO.getUsername());
            patient.setPassword(patientDTO.getPassword());
            patientRepository.save(patient);

        return GlobalStatus.PATIENT_CREATED;
    }

}
