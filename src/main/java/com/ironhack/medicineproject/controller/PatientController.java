package com.ironhack.medicineproject.controller;

import com.ironhack.medicineproject.dto.DoctorDTO;
import com.ironhack.medicineproject.dto.PatientDTO;
import com.ironhack.medicineproject.model.PatientModel;
import com.ironhack.medicineproject.service.PatientService;
import com.ironhack.medicineproject.enums.GlobalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity getAllPatients(){

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
    public ResponseEntity addNewPatient(@RequestBody PatientDTO patientDTO){

        GlobalStatus status = patientService.addNewPatient(patientDTO);

        if (status.equals(GlobalStatus.NAME_TOO_SHORT))
            return
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient Name is too short");
        if (status.equals(GlobalStatus.NAME_TOO_LONG))
            return
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient Name is too long");
        return
                ResponseEntity.status(HttpStatus.CREATED).body(patientDTO);
    }

    @DeleteMapping("/patient")
    public ResponseEntity deletePatient(@RequestBody PatientDTO patientDTO) {

        GlobalStatus status = patientService.deletePatient(patientDTO);

        if (status.equals(GlobalStatus.PATIENT_NOT_FOUND))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");

        if (status.equals(GlobalStatus.PATIENT_DELETED))
            return ResponseEntity.status(HttpStatus.OK).body("Patient " + patientDTO.getPatientTitle() + "." + patientDTO.getPatientLastName() + " deleted");
        return
                ResponseEntity.status(HttpStatus.OK).body(patientDTO);
    }


}
