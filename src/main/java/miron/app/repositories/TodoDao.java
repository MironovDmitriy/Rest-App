package miron.app.repositories;

import miron.app.model.Todo;
import org.springframework.stereotype.Repository;

@Repository
public class TodoDao extends Dao<Todo> {

    @Override
    protected Class<Todo> getPersistentClass() {
        return Todo.class;
    }
}
