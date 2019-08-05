package miron.app.controller;

import lombok.AllArgsConstructor;
import miron.app.model.Todo;
import miron.app.service.TodoService;
import miron.app.view.BootgridTableRequest;
import miron.app.view.BootgridTableResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class MainController {
    private TodoService todoService;

    @GetMapping(value = "/list")
    public List<Todo> getAllTodos() { return todoService.getAll(); }

    @PostMapping(value = "/table", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BootgridTableResponse getTableModel(@RequestBody BootgridTableRequest bootgridTableRequest) {
        return todoService.createBootgridTableModel(bootgridTableRequest);
    }

    @GetMapping(value = "/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable int todoId) {
        Todo todo = todoService.getOneById(todoId);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PostMapping(value = "/todo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.save(todo);

        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable int todoId, @RequestBody Todo todo) {
        Todo inDatabase = todoService.getOneById(todoId);
        inDatabase.setDescription(todo.getDescription());
        Todo saved = todoService.update(inDatabase);

        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{todoId}")
    public ResponseEntity<?> removeTodo(@PathVariable int todoId) {
        Todo inDatabase = todoService.getOneById(todoId);
        Todo removed = todoService.remove(inDatabase);

        return new ResponseEntity<>(removed, HttpStatus.OK);
    }
}
