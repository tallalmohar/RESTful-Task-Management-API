package com.tallalmohar.taskmanager.service;

import com.tallalmohar.taskmanager.dto.AuthResponse;
import com.tallalmohar.taskmanager.dto.LoginRequest;
import com.tallalmohar.taskmanager.dto.RegisterRequest;
import com.tallalmohar.taskmanager.model.User;
import com.tallalmohar.taskmanager.repository.UserRepository;
import com.tallalmohar.taskmanager.security.JWTService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,JWTService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest registerRequest){
        if(this.userRepository.existsByEmail((registerRequest.getEmail()))){
            throw new RuntimeException("Email Already Exists!");
        }
        // If email doesn't exist we make a new one
        User user = new User();
        user.setEmail(registerRequest.getEmail());


        //encode password
        user.setPassword(this.passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
    }

    //TODO : Create an AUTHRESPONSE (DONE)
    public AuthResponse login(LoginRequest loginRequest){
        User user = this.userRepository.findByEmail(loginRequest.getEmail());
        if(user == null){
            throw new RuntimeException("Authentication Failed");
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new RuntimeException("Auth Failed"); //checks password
        }
        //TODO : ADD JWTSERVICE (DONE)
        String token = this.jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);

    }
}
