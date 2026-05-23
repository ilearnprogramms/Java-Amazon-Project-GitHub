package com.ironhack.medicineproject.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
//              .formLogin(Customizer.withDefaults()); // works fine with browser but not postman
                .httpBasic(Customizer.withDefaults())  // for it to work with postman
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails RnTom = User
                .withUsername("tommy")
                .password("{noop}1234")// {noop} = No password encoding for simplicity ({brcypt}?)
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_DOCTOR")))
                .roles("DOCTOR")
                .build();

        UserDetails PhdKim = User
                .withUsername("kimmy")
                .password("{noop}1234")
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_DOCTOR")))
                .roles("DOCTOR")
                .build();

        UserDetails MrBauer = User
                .withUsername("bauer")
                .password("{noop}1234")
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_PATIENT")))
                .roles("PATIENT")
                .build();

        return new InMemoryUserDetailsManager(RnTom, PhdKim, MrBauer);

    }



}
