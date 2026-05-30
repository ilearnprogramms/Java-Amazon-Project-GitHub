package com.ironhack.medicineproject.controller;

import com.ironhack.medicineproject.dto.DoctorDTO;
import com.ironhack.medicineproject.exceptions.SuccessResponse;
import com.ironhack.medicineproject.model.DoctorModel;
import com.ironhack.medicineproject.service.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/welcome")
    public String greet() {
        return "Welcome to Medicine Project!\n ";
    }

    @GetMapping("/doctors")
    public ResponseEntity getAllDoctors() {

        Logger doctorLogger = Logger.getLogger("DoctorController");
        doctorLogger.info("Geting all doctors");

        List<DoctorDTO> doctorDTOs = doctorService.getAllDoctors()
                .stream()
                .map(d -> new DoctorDTO(
                        d.getDrTitle(),
                        d.getDrLastName(),
                        d.getUsername(),
                        d.getPassword()
                ))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(doctorDTOs);
    }

    @PostMapping("/doctor")
    public ResponseEntity addNewDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {

        Logger doctorLogger = Logger.getLogger("DoctorController");
        doctorLogger.info("Adding a new doctor");

        DoctorModel addDoctor = doctorService.addNewDoctor(doctorDTO);
        return
                ResponseEntity.ok(new SuccessResponse("Doctor Added", doctorDTO));
    }

    @DeleteMapping("/doctor")
    public ResponseEntity deleteDoctor(@RequestBody DoctorDTO doctorDTO) {

        Logger doctorLogger = Logger.getLogger("DoctorController");
        doctorLogger.info("Deleting a doctor");

        DoctorModel deletedDoctor = doctorService.deleteDoctor(doctorDTO);
        return
                ResponseEntity.ok(new SuccessResponse("Doctor deleted", doctorDTO));

    }

}
