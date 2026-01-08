package com.tallalmohar.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskRequest {
    @NotBlank
    @Size(max = 255)
    private String title;

    @Size(max = 1000)
    private String description;

    private LocalDate dueDate;
}
