package com.IronHack.Medicine.Project.patients;

import com.IronHack.Medicine.Project.security.GlobalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService  patientService;

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

    @PostMapping("/newpatient")
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
        // TODO ADD MORE ERROR HANDLING
    }


}
