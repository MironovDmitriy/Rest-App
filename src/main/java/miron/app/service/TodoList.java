package miron.app.service;

import lombok.AllArgsConstructor;
import miron.app.exceptions.ResourceNotFoundException;
import miron.app.model.dto.Todo;
import miron.app.repositories.TodosRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoList {
    TodosRepository repository;

    private void todoNotFound(int todoId) {
        String message = "Todo № " + todoId + " is not found";
        throw new ResourceNotFoundException(message);
    }

    public Optional<Todo> getTodo(@RequestParam(value = "todoId") int todoId) {
        Optional<Todo> todo = repository.findById(todoId);
        if(!todo.isPresent()) {
            todoNotFound(todoId);
        }
        return todo;
    }
}
