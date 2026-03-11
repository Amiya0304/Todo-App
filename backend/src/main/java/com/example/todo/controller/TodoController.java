package com.example.todo.controller;

import com.example.todo.DTO.ToDoRequest;
import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return service.getAllTodos();
    }

    @PostMapping
    public Todo createTodo(@RequestBody ToDoRequest dto) {
        return service.createTodo(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        service.deleteTodo(id);
    }

    @PatchMapping("/{id}")
    public void patchTodo(@PathVariable Long id, @RequestBody ToDoRequest dto){
        service.patchTodo(id, dto);
    }
}
