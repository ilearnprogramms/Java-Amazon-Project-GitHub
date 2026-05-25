package com.ironhack.medicineproject.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // store encrypted passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // security checks every request that passes through
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests
                        -> requests
                        .requestMatchers("/api/prescription/mymeds")
                        .hasAnyRole("PATIENT") // access to only own infos as patient

                        .anyRequest().hasRole("DOCTOR") // access to everything if it's a doctor
                )


//              .formLogin(Customizer.withDefaults()); // works fine with browser but not postman

                .httpBasic(Customizer.withDefaults())  // for it to work with postman

                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        // do NOT store login sessions on the server
                        // STATELESS = every request must contain credentials/token
                .build();

    }

}
