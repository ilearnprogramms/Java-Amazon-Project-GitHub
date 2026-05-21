package com.IronHack.Medicine.Project.doctors;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface doctorRepository extends JpaRepository <doctorModel, Long> {

    List<doctorModel> findByDrLastNameIgnoreCase(String drLastName);
    List<doctorModel> findByUsernameIgnoreCase(String username);
}
