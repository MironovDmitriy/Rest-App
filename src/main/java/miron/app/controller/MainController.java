package miron.app.controller;

import lombok.AllArgsConstructor;
import miron.app.model.Todo;
import miron.app.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class MainController {
    private TodoService list;

    @GetMapping(value = "/list")
    public List<Todo> getAllTodos() {
        Collection<Todo> allTodos = list.getAll();

        return new ArrayList<>(allTodos);
    }

    @GetMapping(value = "/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable int todoId) {
        Todo todo = list.getOneById(todoId);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PostMapping(value = "/todo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = list.save(todo);

        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable int todoId, @RequestBody Todo todo) {
        Todo inDatabase = list.getOneById(todoId);
        inDatabase.setDescription(todo.getDescription());
        Todo saved = list.update(inDatabase);

        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{todoId}")
    public ResponseEntity<?> removeTodo(@PathVariable int todoId) {
        Todo inDatabase = list.getOneById(todoId);
        Todo removed = list.remove(inDatabase);

        return new ResponseEntity<>(removed, HttpStatus.OK);
    }
}
