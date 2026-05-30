package com.ironhack.medicineproject.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MyUserDetailsService myUserDetailsService;

    public SecurityConfiguration(
            AuthenticationManagerBuilder authenticationManagerBuilder,
            MyUserDetailsService myUserDetailsService) {

        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.myUserDetailsService = myUserDetailsService;
    }

    // store encrypted passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // security checks every request that passes through
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        CustomAuthorizationFilterLoginUsernamePassword loginFilter =
                new CustomAuthorizationFilterLoginUsernamePassword(authenticationManagerBuilder.getOrBuild());
        loginFilter.setFilterProcessesUrl("/api/login");

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/welcome").permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/prescription/mymeds").hasRole("PATIENT")
                        .requestMatchers("/api/**").hasRole("DOCTOR")
                        //.anyRequest().hasRole("DOCTOR") // all other endpoints, doctor-only
                )
                .addFilter(loginFilter) // login filter for username/password
                .addFilterBefore(
                        new CustomAuthorizationFilterToken(myUserDetailsService),
                        UsernamePasswordAuthenticationFilter.class) // token filter
                .build();

    }
}