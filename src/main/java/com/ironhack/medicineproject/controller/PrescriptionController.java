package com.ironhack.medicineproject.controller;

import com.ironhack.medicineproject.dto.PrescriptionDTO;
import com.ironhack.medicineproject.enums.GlobalStatus;
import com.ironhack.medicineproject.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @PostMapping("/prescription/{patientID}")
    public ResponseEntity<GlobalStatus> addPrescription(
            @PathVariable Long patientID,
            @RequestBody PrescriptionDTO prescriptionDTO
    ) {

        prescriptionDTO.setPatientID(patientID);

        GlobalStatus status =
                prescriptionService.addPrescription(prescriptionDTO);

        return ResponseEntity.ok(status);
    }



}
