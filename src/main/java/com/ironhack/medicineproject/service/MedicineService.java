package com.ironhack.medicineproject.service;

import com.ironhack.medicineproject.dto.MedicineDTO;
import com.ironhack.medicineproject.exceptions.SearchNotFoundException;
import com.ironhack.medicineproject.model.MedicineModel;
import com.ironhack.medicineproject.repository.MedicineRepository;
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

    public MedicineModel addNewMedicine(MedicineDTO medicineDTO){

        MedicineModel medicine = new MedicineModel();
            medicine.setMedicineName(medicineDTO.getMedicineName());
            medicine.setMedicineCategory(medicineDTO.getMedicineCategory());
            medicine.setMedicineQuantity(medicineDTO.getMedicineQuantity());

        return medicineRepository.save(medicine);
    }

    public MedicineModel updateMedicineQuantity(Long medicineID, Integer medicineQuantity){

        final MedicineModel medicine = medicineRepository.findById(medicineID)
                        .orElseThrow(()
                                -> new SearchNotFoundException("Couldn't find medicine with id: " + medicineID));

        medicine.setMedicineQuantity(medicineQuantity);
        return medicineRepository.save(medicine);
    }

    public MedicineModel deleteMedicine (MedicineDTO medicineDTO){

        final MedicineModel medicine = medicineRepository
                .findByMedicineName(medicineDTO.getMedicineName());

        if (medicine == null){
            throw new SearchNotFoundException("Couldn't find the medicine: " +
                      medicineDTO.getMedicineName());
        }

        medicineRepository.delete(medicine);
        return medicine;
    }

}
