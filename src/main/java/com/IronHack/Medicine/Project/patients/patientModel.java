package com.IronHack.Medicine.Project.patients;


import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class patientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientID;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private patientTitles patientTitle;
    private String patientLastName;

    public patientModel(String username, String password, patientTitles patientTitle, String patientLastName) {
        this.username = username;
        this.password = password;
        this.patientTitle = patientTitle;
        this.patientLastName = patientLastName;
    }

    public patientModel() {

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

    public patientTitles getPatientTitle() {
        return patientTitle;
    }

    public void setPatientTitle(patientTitles patientTitle) {
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
