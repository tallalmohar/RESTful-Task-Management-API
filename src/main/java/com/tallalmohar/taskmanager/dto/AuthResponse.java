package com.tallalmohar.taskmanager.dto;

import com.tallalmohar.taskmanager.service.AuthService;

public class AuthResponse {
    private String token;

    public AuthResponse(String token){
        this.token = token;
    }
}
