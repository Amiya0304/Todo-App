package com.example.todo.service;

import com.example.todo.DTO.ToDoRequest;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    public Todo createTodo(ToDoRequest dto) {
        Todo todo = new Todo();
        todo.setName(dto.getName());
        todo.setCompleted(dto.isCompleted());
        return repository.save(todo);
    }

    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }

    public void patchTodo(Long id, ToDoRequest dto){
        Optional<Todo> todoOptional = repository.findById(id);

        if(todoOptional.isPresent()){
            Todo todo = todoOptional.get();

            todo.setName(dto.getName());
            todo.setCompleted(dto.isCompleted());

            repository.save(todo);
        }

    }
}
