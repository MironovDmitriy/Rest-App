package miron.app.controller;

import lombok.AllArgsConstructor;
import miron.app.model.dto.Todo;
import miron.app.service.TodoList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/")
public class MainController {
    TodoList list;

    @GetMapping("{todoId}")
    public Optional<Todo> todoItem(@PathVariable int todoId) {
        return list.getTodo(todoId);
    }
}
