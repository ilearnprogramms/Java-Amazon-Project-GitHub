package com.ironhack.medicineproject.service;

import com.ironhack.medicineproject.dto.MedicineDTO;
import com.ironhack.medicineproject.model.MedicineModel;
import com.ironhack.medicineproject.repository.MedicineRepository;
import com.ironhack.medicineproject.enums.GlobalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
            medicine.setMedicineQuantity(medicineDTO.getMedicineQuantity());
            medicineRepository.save(medicine);

        return GlobalStatus.MEDICINE_CREATED;
    }

    public GlobalStatus deleteMedicine(MedicineDTO medicineDTO){

        final List<MedicineModel> findByMedicineName
                = Collections.singletonList(medicineRepository
                .findByMedicineName(medicineDTO.getMedicineName()));

        if (findByMedicineName.isEmpty())
            return GlobalStatus.MEDICINE_NOT_FOUND;
        MedicineModel medicine = findByMedicineName.get(0);
        medicineRepository.delete(medicine);

        return GlobalStatus.MEDICINE_DELETED;
    }

}
