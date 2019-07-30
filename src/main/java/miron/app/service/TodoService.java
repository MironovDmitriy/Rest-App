package miron.app.service;

import lombok.AllArgsConstructor;
import miron.app.exceptions.ResourceNotFoundException;
import miron.app.model.Todo;
import miron.app.repositories.TodoDao;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoService implements ICrudService<Todo, TodoDao> {
    private TodoDao todoDao;

    @Override
    public TodoDao getDao() { return todoDao; }
}
