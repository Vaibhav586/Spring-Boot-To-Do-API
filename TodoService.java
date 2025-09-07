package com.example.todo.service;

import com.example.todo.dto.TodoDTO;
import com.example.todo.entity.Todo;
import com.example.todo.exception.ResourceNotFoundException;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    public List<Todo> getAll() {
        return repository.findAll();
    }

    public Todo getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));
    }

    public Todo create(TodoDTO dto) {
        Todo todo = Todo.builder()
                .title(dto.getTitle())
                .completed(dto.isCompleted())
                .build();
        return repository.save(todo);
    }

    public Todo update(Long id, TodoDTO dto) {
        Todo existing = getById(id);
        existing.setTitle(dto.getTitle());
        existing.setCompleted(dto.isCompleted());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Todo todo = getById(id);
        repository.delete(todo);
    }
}
