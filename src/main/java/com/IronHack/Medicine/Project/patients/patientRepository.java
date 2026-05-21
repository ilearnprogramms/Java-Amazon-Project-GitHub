package com.IronHack.Medicine.Project.patients;

import org.springframework.data.jpa.repository.JpaRepository;

public interface patientRepository extends JpaRepository <patientModel,Long> {
}
