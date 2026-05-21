package com.IronHack.Medicine.Project.doctors;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class DoctorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorID;

    @Enumerated(EnumType.STRING)
    private DoctorateTitles drTitle;
    private String drLastName;

    private String username;
    private String password;

    public DoctorModel(DoctorateTitles drTitle, String drLastName, String username, String password) {
        this.drTitle = drTitle;
        this.drLastName = drLastName;
        this.username = username;
        this.password = password;
    }

    public DoctorModel() {

    }

    public DoctorateTitles getDrTitle() {
        return drTitle;
    }

    public void setDrTitle(DoctorateTitles drTitle) {
        this.drTitle = drTitle;
    }

    public String getDrLastName() {
        return drLastName;
    }

    public void setDrLastName(String drLastName) {
        this.drLastName = drLastName;
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

    @Override
    public String toString() {
        return "doctorModel{" +
                "doctorID='" + doctorID + '\'' +
                ", drTitle=" + drTitle +
                ", drLastName='" + drLastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + "*" + '\'' +
                '}';
    }
}
