package com.tallalmohar.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userID;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createdAt;

    // Required: JPA Entity Annotation
    public User() { }
}
