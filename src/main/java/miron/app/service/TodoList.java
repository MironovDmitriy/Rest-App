package miron.app.service;

import lombok.AllArgsConstructor;
import miron.app.model.dto.Todo;
import miron.app.repositories.TodosRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoList {
    TodosRepository repository;

    public Optional<Todo> getTodo(int todoId) {
        Optional<Todo> todo = repository.findById(todoId);
        return todo;
    }
}
