package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoDTO {
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private boolean completed;
}
