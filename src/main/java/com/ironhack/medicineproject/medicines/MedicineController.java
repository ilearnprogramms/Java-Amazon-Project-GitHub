package com.ironhack.medicineproject.medicines;

import com.ironhack.medicineproject.security.GlobalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    public ResponseEntity getAllMedicines(){

        List<MedicineDTO> medicineDTOs = medicineService.getAllMedicines()
                .stream()
                .map(m -> new MedicineDTO(
                        m.getMedicineName(),
                        m.getMedicineCategory() // HUMAN OR VETERINARY
                ))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(medicineDTOs);
    }


    @PostMapping("/newmedicine")
    public ResponseEntity addNewMedicine(@RequestBody MedicineDTO medicineDTO){

        GlobalStatus status = medicineService.addNewMedicine(medicineDTO);

        if (status.equals(GlobalStatus.NAME_TOO_SHORT))
            return
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Medicine name is too short");
        if (status.equals(GlobalStatus.NAME_TOO_LONG))
            return
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pretty long name for a medicine");
        return
                ResponseEntity.status(HttpStatus.CREATED).body(medicineDTO);
        // TODO ADD MORE ERROR HANDLING
    }

}
