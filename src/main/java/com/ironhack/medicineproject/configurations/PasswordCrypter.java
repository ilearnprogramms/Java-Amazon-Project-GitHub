package com.ironhack.medicineproject.configurations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCrypter {

    // JUST A QUICK JAVA TOOL TO CREATE CRYPTED PASSWORDS
    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        System.out.println(encoder.encode("123456789"));
    }
}
