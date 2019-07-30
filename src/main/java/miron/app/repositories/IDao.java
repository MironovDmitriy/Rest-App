package miron.app.repositories;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IDao<T> {
    T get (int id);

    Collection<T> getAll();

    T update(T model);

    T save(T model);

    T remove(T model);
}
