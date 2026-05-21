package com.IronHack.Medicine.Project.doctors;

import com.IronHack.Medicine.Project.security.globalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api/doctor")
public class doctorController {

    @Autowired
    private doctorService doctorService;

    // TODO LOGIN

    @GetMapping("/doctors")
    public ResponseEntity getAllDoctors() {

        List<doctorDTO> doctorDTOs = doctorService.getAllDoctors()
                .stream()
                .map(d -> new doctorDTO(
                        d.getDrTitle(),
                        d.getDrLastName(),
                        d.getUsername(),
                        d.getPassword()
                ))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(doctorDTOs);
    }


    @PostMapping("/newdoctor")
    public ResponseEntity addNewDoctor(@RequestBody doctorDTO doctorDTO) {

        globalStatus status = doctorService.addNewDoctor(doctorDTO);

        if (status.equals(globalStatus.NAME_TOO_SHORT))
            return
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is too short");
        if (status.equals(globalStatus.NAME_TOO_LONG))
            return
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is too long");
        return
                ResponseEntity.status(HttpStatus.CREATED).body(doctorDTO);
    }

}
