package miron.app.controller;

import lombok.AllArgsConstructor;
import miron.app.model.dto.Todo;
import miron.app.service.TodoList;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/")
public class MainController {
    TodoList list;

//    @GetMapping()
//    public Optional<Todo> todoItem(@RequestParam int todoId) {
//        return list.getTodo(todoId);
//    }

    @GetMapping("{todoId}")
    public Optional<Todo> todoItem(@PathVariable int todoId) {
        return list.getTodo(todoId);
    }
}
