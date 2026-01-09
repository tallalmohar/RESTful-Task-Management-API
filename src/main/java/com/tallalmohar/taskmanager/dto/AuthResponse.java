package com.tallalmohar.taskmanager.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
public class AuthResponse {
    private String token;

    public AuthResponse(String token){
        this.token = token;
    }
}
