package com.ironhack.medicineproject.controller;

import com.ironhack.medicineproject.configurations.CustomUserDetails;
import com.ironhack.medicineproject.dto.PrescriptionDTO;
import com.ironhack.medicineproject.exceptions.SuccessResponse;
import com.ironhack.medicineproject.model.PrescriptionModel;
import com.ironhack.medicineproject.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @GetMapping("/prescriptions")
    public ResponseEntity getAllPrescriptions(){

        Logger prescriptionsLogger = Logger.getLogger("PrescriptionController");
        prescriptionsLogger.info("Getting all Prescriptions");

        List<PrescriptionDTO> prescriptionDTOS = prescriptionService.getAllPrescriptions()
                .stream()
                .map( p -> new PrescriptionDTO(
                        p.getDoctor().getDoctorID(),
                        p.getPatient().getPatientID(),
                        p.getMedicine().getMedicineID(),
                        p.getDescription(),
                        p.getPrescribedDate()
                ))
                .toList();
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/prescription/mymeds")
    public List<PrescriptionModel> getMyPrescriptions(Authentication authentication) {

        Logger prescriptionsLogger = Logger.getLogger("PrescriptionController");
        prescriptionsLogger.info("Getting Prescriptions for The Logged in Patient");

        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        assert user != null;
        Long patientID = user.getId();

        return prescriptionService.getByPatientID(patientID);
    }

    @PostMapping("/prescription/{patientID}")
    public ResponseEntity<?> addPrescription(
            @PathVariable Long patientID,
            @Valid @RequestBody PrescriptionDTO prescriptionDTO
    ) {

        Logger prescriptionsLogger = Logger.getLogger("PrescriptionController");
        prescriptionsLogger.info("Adding a new Prescription");

        prescriptionDTO.setPatientID(patientID);

        PrescriptionModel addPrescription = prescriptionService.addPrescription(prescriptionDTO);
        return
                ResponseEntity.ok(new SuccessResponse("Prescription Added to the patient ", prescriptionDTO));

    }


    @DeleteMapping("/prescription")
    public ResponseEntity<?> deletePrescription(
            @RequestBody PrescriptionDTO prescriptionDTO) {

        Logger prescriptionsLogger = Logger.getLogger("PrescriptionController");
        prescriptionsLogger.info("Deleting a Prescription");

        PrescriptionModel deletedPrescription = prescriptionService.deletePrescription(prescriptionDTO);
        return
                ResponseEntity.ok(new SuccessResponse("Prescription deleted", prescriptionDTO.getPrescriptionID()));
    }
}
