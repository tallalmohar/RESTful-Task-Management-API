package com.tallalmohar.taskmanager.controller;

import com.tallalmohar.taskmanager.dto.AuthResponse;
import com.tallalmohar.taskmanager.dto.LoginRequest;
import com.tallalmohar.taskmanager.dto.RegisterRequest;
import com.tallalmohar.taskmanager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService authService;
    public AuthController( AuthService as){
        this.authService = as;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest request)
    {
        this.authService.register(request);
        return new ResponseEntity<>("User Registered!!",HttpStatus.CREATED);
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest request)
    {
        return new ResponseEntity<>(this.authService.login(request),HttpStatus.OK);
    }
}
