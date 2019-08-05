package miron.app.dao;

import miron.app.model.Todo;
import org.springframework.stereotype.Repository;

@Repository
public class TodoDao extends FiltrableDaoBase<Todo> {

    @Override
    protected Class<Todo> getPersistentClass() {
        return Todo.class;
    }
}
