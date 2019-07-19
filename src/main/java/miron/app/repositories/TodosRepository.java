package miron.app.repositories;

import miron.app.model.dto.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodosRepository extends JpaRepository<Todo, Integer> {
}