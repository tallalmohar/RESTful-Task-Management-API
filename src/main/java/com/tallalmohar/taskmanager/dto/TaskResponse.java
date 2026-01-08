package com.tallalmohar.taskmanager.dto;

import com.tallalmohar.taskmanager.model.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskResponse{

    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
}
