package com.IronHack.Medicine.Project.patients;


import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class PatientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientID;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private PatientTitles patientTitle;
    private String patientLastName;

    public PatientModel(String username, String password, PatientTitles patientTitle, String patientLastName) {
        this.username = username;
        this.password = password;
        this.patientTitle = patientTitle;
        this.patientLastName = patientLastName;
    }

    public PatientModel() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PatientTitles getPatientTitle() {
        return patientTitle;
    }

    public void setPatientTitle(PatientTitles patientTitle) {
        this.patientTitle = patientTitle;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    @Override
    public String toString() {
        return "patientModel{" +
                "patientID='" + patientID + '\'' +
                ", username='" + username + '\'' +
                ", password='" + "*" + '\'' +
                ", patientTitle=" + patientTitle +
                ", patientLastName='" + patientLastName + '\'' +
                '}';
    }
}
