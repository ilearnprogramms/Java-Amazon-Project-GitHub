package com.ironhack.medicineproject.controller;

import com.ironhack.medicineproject.dto.DoctorDTO;
import com.ironhack.medicineproject.service.DoctorService;
import com.ironhack.medicineproject.enums.GlobalStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // TODO LOGIN
    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return "Welcome to Medicine Project!\n " + request.getSession().getId();
    }

//    @GetMapping("/csrf-token")
//    public CsrfToken getCsrfToken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }

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
    public ResponseEntity addNewDoctor(@RequestBody DoctorDTO doctorDTO) {

        Logger doctorLogger = Logger.getLogger("DoctorController");
        doctorLogger.info("Adding a new doctor");


        GlobalStatus status = doctorService.addNewDoctor(doctorDTO);

        if (status.equals(GlobalStatus.NAME_TOO_SHORT))
            return
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is too short");
        if (status.equals(GlobalStatus.NAME_TOO_LONG))
            return
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is too long");
        return
                ResponseEntity.status(HttpStatus.CREATED).body(doctorDTO);
        // TODO ADD MORE ERROR HANDLING
    }

    @DeleteMapping("/doctor")
    public ResponseEntity deleteDoctor(@RequestBody DoctorDTO doctorDTO) {

        Logger doctorLogger = Logger.getLogger("DoctorController");
        doctorLogger.info("Deleting a doctor");

        GlobalStatus status = doctorService.deleteDoctor(doctorDTO);

        if (status.equals(GlobalStatus.DOCTOR_NOT_FOUND))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");

        if (status.equals(GlobalStatus.DOCTOR_DELETED))
            return ResponseEntity.status(HttpStatus.OK).body("Doctor deleted");
        return
                ResponseEntity.status(HttpStatus.OK).body("Doctor deleted");
    }

}
