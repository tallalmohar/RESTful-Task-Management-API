package com.tallalmohar.taskmanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        // TODO: add JWT filter + authentication provider once JWT validation is implemented
        // this was done form a tutorial still have to understand
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(csrf -> csrf.disable())
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults());
                return http.build();
        }
}