package com.ironhack.medicineproject.controller;

import com.ironhack.medicineproject.dto.PatientDTO;
import com.ironhack.medicineproject.exceptions.SuccessResponse;
import com.ironhack.medicineproject.model.PatientModel;
import com.ironhack.medicineproject.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity getAllPatients(){

        Logger patientLogger = Logger.getLogger("PatientController");
        patientLogger.info("Getting all Patients");

        List<PatientDTO> patientDTOs = patientService.getAllPatients()
                .stream()
                .map( p -> new PatientDTO(
                        p.getPatientTitle(),
                        p.getPatientLastName(),
                        p.getUsername(),
                        p.getPassword()
                ))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(patientDTOs);
    }

    @PostMapping("/patient")
    public ResponseEntity addNewPatient(@Valid @RequestBody PatientDTO patientDTO){

        Logger patientLogger = Logger.getLogger("PatientController");
        patientLogger.info("Adding a new Patient");

        PatientModel addPatient = patientService.addNewPatient(patientDTO);
        return
                ResponseEntity.ok(new SuccessResponse("Patient added", patientDTO));
    }

    @DeleteMapping("/patient") // Delete patient with their username
    public ResponseEntity<?> deletePatient(@RequestBody PatientDTO patientDTO) {

        Logger patientLogger = Logger.getLogger("PatientController");
        patientLogger.info("Deleting a Patient");

        PatientModel deletedPatient = patientService.deletePatient(patientDTO);
        return
                ResponseEntity.ok(new SuccessResponse("Patient deleted", patientDTO));
    }


}
