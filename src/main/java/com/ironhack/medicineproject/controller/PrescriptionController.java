package com.ironhack.medicineproject.controller;

import com.ironhack.medicineproject.configurations.CustomUserDetails;
import com.ironhack.medicineproject.dto.PrescriptionDTO;
import com.ironhack.medicineproject.enums.GlobalStatus;
import com.ironhack.medicineproject.model.PrescriptionModel;
import com.ironhack.medicineproject.service.PrescriptionService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    // TODO PATIENT INFO IS BEING RETURNED AS NULL
    @GetMapping("/prescription")
    public ResponseEntity getAllPrescriptions(){
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }


    // TODO METHOD WORKS BUT PRINTS NOTHING
    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/prescription/mymeds")
    public List<PrescriptionModel> getMyPrescriptions(
            Authentication authentication) {

        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        Long patientID = user.getId();

        return prescriptionService.getByPatientID(patientID);
    }

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
