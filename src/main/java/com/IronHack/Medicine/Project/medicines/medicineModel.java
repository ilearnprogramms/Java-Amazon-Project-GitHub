package com.IronHack.Medicine.Project.medicines;


import jakarta.persistence.*;

@Entity
@Table(name = "medicines")
public class medicineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineID;

    private String medicineName;
    private String medicineInstruction;

    @Enumerated(EnumType.STRING)
    private medicineCategory medicineCategory;

    public medicineModel(String medicineName, String medicineInstruction, medicineCategory medicineCategory) {
        this.medicineName = medicineName;
        this.medicineInstruction = medicineInstruction;
        this.medicineCategory = medicineCategory;
    }

    public medicineModel() {

    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineInstruction() {
        return medicineInstruction;
    }

    public void setMedicineInstruction(String medicineInstruction) {
        this.medicineInstruction = medicineInstruction;
    }

    public medicineCategory getMedicineCategory() {
        return medicineCategory;
    }

    public void setMedicineCategory(medicineCategory medicineCategory) {
        this.medicineCategory = medicineCategory;
    }

    @Override
    public String toString() {
        return "medicineModel{" +
                "medicineID=" + medicineID +
                ", medicineName='" + medicineName + '\'' +
                ", medicineInstruction='" + medicineInstruction + '\'' +
                ", medicineCategory='" + medicineCategory + '\'' +
                '}';
    }
}
