package com.IronHack.Medicine.Project.medicines;


import jakarta.persistence.*;

@Entity
@Table(name = "medicines")
public class MedicineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineID;

    private String medicineName;

    @Enumerated(EnumType.STRING)
    private MedicineCategory medicineCategory;

    public MedicineModel(String medicineName, MedicineCategory medicineCategory) {
        this.medicineName = medicineName;
        this.medicineCategory = medicineCategory;
    }

    public MedicineModel() {

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
