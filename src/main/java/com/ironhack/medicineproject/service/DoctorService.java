package com.ironhack.medicineproject.service;

import com.ironhack.medicineproject.dto.DoctorDTO;
import com.ironhack.medicineproject.exceptions.SearchNotFoundException;
import com.ironhack.medicineproject.model.DoctorModel;
import com.ironhack.medicineproject.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<DoctorModel> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public DoctorModel addNewDoctor(DoctorDTO doctorDTO){

                DoctorModel doctor = new DoctorModel();
                doctor.setDrTitle(doctorDTO.getDrTitle());
                doctor.setDrLastName(doctorDTO.getDrLastName());
                doctor.setUsername(doctorDTO.getUsername());
                doctor.setPassword(doctorDTO.getPassword());

        return doctorRepository.save(doctor);
    }

    public DoctorModel deleteDoctor(DoctorDTO doctorDTO){

        final DoctorModel doctor = doctorRepository
                .findByUsernameIgnoreCase(doctorDTO.getUsername());

        if (doctor == null) {
            throw new SearchNotFoundException("Couldn't find the Doctor: " +
                      doctorDTO.getUsername());
        }
        doctorRepository.delete(doctor);
        return doctor;
    }

}
