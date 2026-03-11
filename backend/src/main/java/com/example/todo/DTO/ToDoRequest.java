package com.example.todo.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoRequest {
    @NotNull
    String name;

    @NotNull
    boolean isCompleted;
}
