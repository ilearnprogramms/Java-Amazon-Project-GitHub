package com.ironhack.medicineproject.service;

import com.ironhack.medicineproject.dto.DoctorDTO;
import com.ironhack.medicineproject.model.DoctorModel;
import com.ironhack.medicineproject.repository.DoctorRepository;
import com.ironhack.medicineproject.enums.GlobalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<DoctorModel> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public GlobalStatus addNewDoctor(DoctorDTO doctorDTO){
        if (doctorDTO.getDrLastName().length() < 3)
            return GlobalStatus.NAME_TOO_SHORT;

        if (doctorDTO.getDrLastName().length() > 19)
            return GlobalStatus.NAME_TOO_LONG;

        DoctorModel doctor = new DoctorModel();
                doctor.setDrTitle(doctorDTO.getDrTitle());
                doctor.setDrLastName(doctorDTO.getDrLastName());
                doctor.setUsername(doctorDTO.getUsername());
                doctor.setPassword(doctorDTO.getPassword());
                doctorRepository.save(doctor);

        return GlobalStatus.DOCTOR_CREATED;
    }

    public GlobalStatus deleteDoctor(DoctorDTO doctorDTO){

        final List<DoctorModel> findByUsername
                = Collections.singletonList(doctorRepository
                .findByUsernameIgnoreCase(doctorDTO.getUsername()));
        if (findByUsername.isEmpty())
            return GlobalStatus.DOCTOR_NOT_FOUND;
        DoctorModel doctor = findByUsername.get(0);
        doctorRepository.delete(doctor);
        return GlobalStatus.DOCTOR_DELETED;
    }

}
