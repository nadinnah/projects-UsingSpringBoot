package com.example.tacocloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()  // allow H2 console
                        .anyRequest().authenticated()                   // other endpoints require authentication
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")    // disable CSRF for H2 console
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())    // allow frames for H2 console
                )
                .formLogin(form -> form.permitAll());             // default login page for other endpoints

        return http.build();
    }
}