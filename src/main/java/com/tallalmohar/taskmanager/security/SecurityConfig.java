package com.tallalmohar.taskmanager.security;

import com.tallalmohar.taskmanager.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final JWTService jwtService;
    private final AuthService authService;

    public SecurityConfig(JWTService jwtService, AuthService authService){
        this.jwtService = jwtService;
        this.authService = authService;
    }

    // TODO : Figure this out??
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return null;
    }
}
