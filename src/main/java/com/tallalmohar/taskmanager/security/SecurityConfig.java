package com.tallalmohar.taskmanager.security;

import com.tallalmohar.taskmanager.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
        private final JWTService jwtService;
        private final AuthService authService;

        public SecurityConfig(JWTService jwtService, AuthService authService) {
                this.jwtService = jwtService;
                this.authService = authService;
        }

        // TODO : Figure this out??
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/auth/**").permitAll()
                                                .requestMatchers("/api/tasks/**").authenticated()
                                                .anyRequest().authenticated())
                                .exceptionHandling(exception -> exception.authenticationEntryPoint(
                                                (request, response, authException) -> response.sendError(
                                                                HttpServletResponse.SC_UNAUTHORIZED,
                                                                authException.getMessage())))
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }
}
