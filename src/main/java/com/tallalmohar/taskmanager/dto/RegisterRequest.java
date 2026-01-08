package com.tallalmohar.taskmanager.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(min=6,max = 100)
    private String password;

}
