package com.IronHack.Medicine.Project.doctors;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class doctorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorID;

    @Enumerated(EnumType.STRING)
    private doctorateTitles drTitle;
    private String drLastName;

    private String username;
    private String password;

    public doctorModel(doctorateTitles drTitle, String drLastName, String username, String password) {
        this.drTitle = drTitle;
        this.drLastName = drLastName;
        this.username = username;
        this.password = password;
    }

    public doctorModel() {

    }

    public doctorateTitles getDrTitle() {
        return drTitle;
    }

    public void setDrTitle(doctorateTitles drTitle) {
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
