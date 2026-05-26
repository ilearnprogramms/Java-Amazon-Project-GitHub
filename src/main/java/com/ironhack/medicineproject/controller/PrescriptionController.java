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

@RestController
@RequestMapping("/api")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @GetMapping("/prescriptions")
    public ResponseEntity getAllPrescriptions(){

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
    public List<PrescriptionModel> getMyPrescriptions(
            Authentication authentication) {

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

        prescriptionDTO.setPatientID(patientID);

        GlobalStatus status =
                prescriptionService.addPrescription(prescriptionDTO);

        return ResponseEntity.ok(status);
    }

    @DeleteMapping("/prescription")
    public ResponseEntity deletePrescription(
            @RequestBody PrescriptionDTO prescriptionDTO) {

        GlobalStatus status = prescriptionService.deletePrescription(prescriptionDTO);

        if (status.equals(GlobalStatus.PRESCRIPTION_NOT_FOUND))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prescription not found");

        return
                ResponseEntity.status(HttpStatus.OK).body("Prescription deleted");
    }

}
