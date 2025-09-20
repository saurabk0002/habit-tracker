package com.example.habit_tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF disable
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // sab endpoints allow
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // popup hatane ke liye
                .formLogin(form -> form.disable()); // form login bhi band

        return http.build();
    }

    // PasswordEncoder bean add karna zaroori hai
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}