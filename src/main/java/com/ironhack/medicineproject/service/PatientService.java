package com.ironhack.medicineproject.service;

import com.ironhack.medicineproject.dto.PatientDTO;
import com.ironhack.medicineproject.exceptions.SearchNotFoundException;
import com.ironhack.medicineproject.model.PatientModel;
import com.ironhack.medicineproject.repository.PatientRepository;
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

    public PatientModel addNewPatient(PatientDTO patientDTO) {

        PatientModel patient = new PatientModel();
            patient.setPatientTitle(patientDTO.getPatientTitle());
            patient.setPatientLastName(patientDTO.getPatientLastName());
            patient.setUsername(patientDTO.getUsername());
            patient.setPassword(patientDTO.getPassword());

        return patientRepository.save(patient);
    }

    public PatientModel deletePatient(PatientDTO patientDTO){

        PatientModel findByUsername = patientRepository
                .findByUsernameIgnoreCase(patientDTO.getUsername());

        if (findByUsername == null) {
            throw new SearchNotFoundException("Couldn't find patients with the user: " +
                    patientDTO.getUsername());
        }
        patientRepository.delete(findByUsername);
        return null;
    }
}
