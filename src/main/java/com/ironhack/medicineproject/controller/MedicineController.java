package com.ironhack.medicineproject.controller;

import com.ironhack.medicineproject.dto.MedicineDTO;
import com.ironhack.medicineproject.exceptions.SuccessResponse;
import com.ironhack.medicineproject.model.MedicineModel;
import com.ironhack.medicineproject.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    public ResponseEntity getAllMedicines() {

        Logger medicineLogger = Logger.getLogger("MedicineController");
        medicineLogger.info("Getting all Medicines");

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
    public ResponseEntity addNewMedicine(@Valid @RequestBody MedicineDTO medicineDTO) {

        Logger medicineLogger = Logger.getLogger("MedicineController");
        medicineLogger.info("Posting the following medicine: " +  medicineDTO.getMedicineName());

        MedicineModel addMedicine = medicineService.addNewMedicine(medicineDTO);
        return
                ResponseEntity.ok(new SuccessResponse("Medicine added", medicineDTO));
    }

    @PutMapping("/medicine/{medicineID}")
    public ResponseEntity<?> updateMedicine (@PathVariable Long medicineID,
                                             @RequestBody MedicineDTO medicineDTO){

        Logger medicineLogger = Logger.getLogger("MedicineController");
        medicineLogger.info("Updating quantity for: " + medicineID);

        MedicineModel updatedMedicine =
                medicineService.updateMedicineQuantity(medicineID, medicineDTO.getMedicineQuantity());
        return
                ResponseEntity.ok(new SuccessResponse("Medicine quantity updated", updatedMedicine));
    }

    @DeleteMapping("/medicine")
    public ResponseEntity<?> deleteMedicine(@RequestBody MedicineDTO medicineDTO) {

        Logger medicineLogger = Logger.getLogger("MedicineController");
        medicineLogger.info("Deleting the medicine: " +  medicineDTO.getMedicineName());

        MedicineModel deletedMedicine = medicineService.deleteMedicine(medicineDTO);
        return
                ResponseEntity.ok(new SuccessResponse("Medicine deleted", medicineDTO.getMedicineName()));

    }

}
