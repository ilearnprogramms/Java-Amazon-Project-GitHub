package com.IronHack.Medicine.Project.doctors;

import com.IronHack.Medicine.Project.security.globalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class doctorService {

    @Autowired
    private doctorRepository doctorRepository;

    public List<doctorModel> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public globalStatus addNewDoctor(doctorDTO doctorDTO){
        if (doctorDTO.getDrLastName().length() < 3)
            return globalStatus.NAME_TOO_SHORT;

        if (doctorDTO.getDrLastName().length() > 19)
            return globalStatus.NAME_TOO_LONG;

        doctorModel doctor = new doctorModel();
                doctor.setDrTitle(doctorDTO.getDrTitle());
                doctor.setDrLastName(doctorDTO.getDrLastName());
                doctor.setUsername(doctorDTO.getUsername());
                doctor.setPassword(doctorDTO.getPassword());
                doctorRepository.save(doctor);

        return globalStatus.DOCTOR_CREATED;
    }

}
