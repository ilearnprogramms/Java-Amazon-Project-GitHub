package com.ironhack.medicineproject.configurations;

import com.ironhack.medicineproject.model.DoctorModel;
import com.ironhack.medicineproject.model.PatientModel;
import com.ironhack.medicineproject.repository.DoctorRepository;
import com.ironhack.medicineproject.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DoctorModel doctor = doctorRepo.findByUsernameIgnoreCase(username);

        if (doctor != null) {
            return User.builder()
                    .username(doctor.getUsername())
                    .password(doctor.getPassword())
                    .roles("DOCTOR")
                    .build();
        }

        PatientModel patient = patientRepo.findByUsernameIgnoreCase(username);

        if (patient != null) {
            return User.builder()
                    .username(patient.getUsername())
                    .password(patient.getPassword())
                    .roles("PATIENT")
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }



}
