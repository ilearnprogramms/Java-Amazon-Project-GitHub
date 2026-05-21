package com.IronHack.Medicine.Project.doctors;

import com.IronHack.Medicine.Project.security.GlobalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // TODO LOGIN

    @GetMapping("/doctors")
    public ResponseEntity getAllDoctors() {

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


    @PostMapping("/newdoctor")
    public ResponseEntity addNewDoctor(@RequestBody DoctorDTO doctorDTO) {

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

}
