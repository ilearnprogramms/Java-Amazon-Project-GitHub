package com.IronHack.Medicine.Project.prescription;

import com.IronHack.Medicine.Project.doctors.DoctorModel;
import com.IronHack.Medicine.Project.medicines.MedicineModel;
import com.IronHack.Medicine.Project.patients.PatientModel;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
public class PrescriptionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate prescribedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private PatientModel patient;
    @ManyToOne(fetch = FetchType.LAZY)
    private DoctorModel doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    private MedicineModel medicine;

    public PrescriptionModel(String description, LocalDate prescribedDate, PatientModel patient, DoctorModel doctor, MedicineModel medicine) {
        this.description = description;
        this.prescribedDate = prescribedDate;
        this.patient = patient;
        this.doctor = doctor;
        this.medicine = medicine;
    }

    public PrescriptionModel() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPrescribedDate() {
        return prescribedDate;
    }

    public void setPrescribedDate(LocalDate prescribedDate) {
        this.prescribedDate = prescribedDate;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    public DoctorModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorModel doctor) {
        this.doctor = doctor;
    }

    public MedicineModel getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicineModel medicine) {
        this.medicine = medicine;
    }
}
