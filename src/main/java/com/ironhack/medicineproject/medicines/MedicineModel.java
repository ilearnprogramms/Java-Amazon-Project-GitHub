package com.ironhack.medicineproject.medicines;


import com.ironhack.medicineproject.prescription.PrescriptionModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "medicines")
public class MedicineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineID;

    private String medicineName;

    @Enumerated(EnumType.STRING)
    private MedicineCategory medicineCategory;

    @OneToMany(mappedBy = "medicine",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PrescriptionModel> prescriptions;

    public MedicineModel(String medicineName, MedicineCategory medicineCategory) {
        this.medicineName = medicineName;
        this.medicineCategory = medicineCategory;
    }

    public MedicineModel() {

    }

    public Long getMedicineID() {
        return medicineID;
    }

    public List<PrescriptionModel> getPrescriptions() {
        return prescriptions;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public MedicineCategory getMedicineCategory() {
        return medicineCategory;
    }

    public void setMedicineCategory(MedicineCategory medicineCategory) {
        this.medicineCategory = medicineCategory;
    }

    @Override
    public String toString() {
        return "medicineModel{" +
                "medicineID=" + medicineID +
                ", medicineName='" + medicineName + '\'' +
                ", medicineCategory='" + medicineCategory + '\'' +
                '}';
    }
}
