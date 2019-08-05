package miron.app.service;

import miron.app.model.PersistantModel;
import miron.app.dao.Dao;

import java.util.List;

public interface ICrudService<T extends PersistantModel, D extends Dao<T>> {

    default T getOneById(int id) { return getDao().get(id); }

    default List<T> getAll() { return getDao().getAll(); }

    default T update(T updating) { return getDao().update(updating); }

    default T save(T saved) { return getDao().save(saved); }

    default T remove(T removing) { return getDao().remove(removing); }

    D getDao();
}
