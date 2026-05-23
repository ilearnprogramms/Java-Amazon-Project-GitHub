package com.ironhack.medicineproject.medicines;

import com.ironhack.medicineproject.security.GlobalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    public List<MedicineModel> getAllMedicines(){
        return medicineRepository.findAll();
    }

    public GlobalStatus addNewMedicine(MedicineDTO medicineDTO){

        if(medicineDTO.getMedicineName().length() < 3)
            return GlobalStatus.NAME_TOO_SHORT;

        if (medicineDTO.getMedicineName().length() > 30)
            return GlobalStatus.NAME_TOO_LONG;

        MedicineModel medicine = new MedicineModel();
            medicine.setMedicineName(medicineDTO.getMedicineName());
            medicine.setMedicineCategory(medicineDTO.getMedicineCategory());
            medicineRepository.save(medicine);

        return GlobalStatus.MEDICINE_CREATED;
    }

}
