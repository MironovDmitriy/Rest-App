package miron.app.dao;

import miron.app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodosRepository extends JpaRepository<Todo, Integer> {
}
