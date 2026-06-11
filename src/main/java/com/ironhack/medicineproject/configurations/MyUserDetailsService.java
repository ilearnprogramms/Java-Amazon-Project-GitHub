package com.ironhack.medicineproject.configurations;

import com.ironhack.medicineproject.model.DoctorModel;
import com.ironhack.medicineproject.model.PatientModel;
import com.ironhack.medicineproject.repository.DoctorRepository;
import com.ironhack.medicineproject.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


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

            List<GrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"));

            return new CustomUserDetails(
                    doctor.getDoctorID(),
                    doctor.getUsername(),
                    doctor.getPassword(),
                    authorities
            );
        }

        PatientModel patient = patientRepo.findByUsernameIgnoreCase(username);

        if (patient != null) {

            List<GrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority("ROLE_PATIENT"));

            return new CustomUserDetails(
                    patient.getPatientID(),
                    patient.getUsername(),
                    patient.getPassword(),
                    authorities
            );
        }

        throw new UsernameNotFoundException("We couldn't find any Medical Staff or Patient with the given credentials");
    }

}
