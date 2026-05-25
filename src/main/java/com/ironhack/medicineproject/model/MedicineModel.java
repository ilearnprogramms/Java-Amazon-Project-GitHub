package com.ironhack.medicineproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.medicineproject.enums.MedicineCategory;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "medicines")
public class MedicineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineID;

    private String medicineName;
    private Integer medicineQuantity;


    @Enumerated(EnumType.STRING)
    private MedicineCategory medicineCategory;

    @OneToMany(mappedBy = "medicine",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PrescriptionModel> prescriptions;

    public MedicineModel(String medicineName, MedicineCategory medicineCategory, Integer medicineQuantity) {
        this.medicineName = medicineName;
        this.medicineCategory = medicineCategory;
        this.medicineQuantity = medicineQuantity;
    }

    public MedicineModel() {

    }

    public Long getMedicineID() {
        return medicineID;
    }

    public List<PrescriptionModel> getPrescriptions() {
        return prescriptions;
    }

    public void setMedicineID(Long medicineID) {
        this.medicineID = medicineID;
    }

    public Integer getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(Integer medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
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
