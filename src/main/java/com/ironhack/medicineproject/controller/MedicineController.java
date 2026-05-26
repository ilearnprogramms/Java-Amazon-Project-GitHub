package com.ironhack.medicineproject.controller;

import com.ironhack.medicineproject.dto.MedicineDTO;
import com.ironhack.medicineproject.service.MedicineService;
import com.ironhack.medicineproject.enums.GlobalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    public ResponseEntity getAllMedicines(){

        List<MedicineDTO> medicineDTOs = medicineService.getAllMedicines()
                .stream()
                .map(m -> new MedicineDTO(
                        m.getMedicineName(),
                        m.getMedicineCategory(), // HUMAN OR VETERINARY
                        m.getMedicineQuantity()
                ))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(medicineDTOs);
    }


    @PostMapping("/medicine")
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
    }

    @DeleteMapping("/medicine")
    public ResponseEntity deleteMedicine(@RequestBody MedicineDTO medicineDTO){

        GlobalStatus status = medicineService.deleteMedicine(medicineDTO);

        if (status.equals(GlobalStatus.MEDICINE_NOT_FOUND))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicine not found");

        if (status.equals(GlobalStatus.MEDICINE_DELETED))
            return ResponseEntity.status(HttpStatus.OK).body("Medicine deleted");
        return
                ResponseEntity.status(HttpStatus.OK).body("Medicine deleted");

    }

}
