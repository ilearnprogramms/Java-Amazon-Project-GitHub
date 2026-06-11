package com.ironhack.medicineproject.configurations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCrypter {

    /* JUST A QUICK JAVA TOOL TO CREATE CRYPTED PASSWORDS.
    WHEN YOU CREATE NEW PATIENTS OR MEDICAL STAFF,
    YOU CAN ENTER THE CRYPTED PASSWORD INTO THE POST METHODS
    AND THEY CAN STILL LOG IN WITH THE UNCODED PASSWORD */

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        System.out.println(encoder.encode("123456789"));
    }
}
