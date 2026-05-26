package com.ironhack.medicineproject.controller;

import com.ironhack.medicineproject.configurations.CustomUserDetails;
import com.ironhack.medicineproject.dto.PrescriptionDTO;
import com.ironhack.medicineproject.enums.GlobalStatus;
import com.ironhack.medicineproject.model.PrescriptionModel;
import com.ironhack.medicineproject.service.PrescriptionService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity addPrescription(
            @PathVariable Long patientID,
            @RequestBody PrescriptionDTO prescriptionDTO
    ) {

        Logger prescriptionsLogger = Logger.getLogger("PrescriptionController");
        prescriptionsLogger.info("Adding a new Prescription");

        prescriptionDTO.setPatientID(patientID);

        GlobalStatus status =
                prescriptionService.addPrescription(prescriptionDTO);

        return ResponseEntity.ok(status);
    }

    @DeleteMapping("/prescription")
    public ResponseEntity deletePrescription(
            @RequestBody PrescriptionDTO prescriptionDTO) {

        Logger prescriptionsLogger = Logger.getLogger("PrescriptionController");
        prescriptionsLogger.info("Deleting a Prescription");

        GlobalStatus status = prescriptionService.deletePrescription(prescriptionDTO);

        if (status.equals(GlobalStatus.PRESCRIPTION_NOT_FOUND))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prescription not found");

        return
                ResponseEntity.status(HttpStatus.OK).body("Prescription deleted");
    }

}
