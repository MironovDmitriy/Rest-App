package miron.app.repositories;

import miron.app.model.PersistantModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public abstract class Dao<T extends PersistantModel> implements IDao<T> {
    @Autowired
    protected EntityManager entityManager;

    @Override
    @Transactional
    public T get(int id) {
        T query = entityManager.find(getPersistentClass(), id);
        if (query == null) {
            throw new RuntimeException("По данному ID ничего не было найдено");
        }

        return query;
    }

    @Override
    @Transactional
    public Collection<T> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getPersistentClass());
        TypedQuery<T> allQuery = entityManager.createQuery(cq.select(cq.from(getPersistentClass())));

        Collection<T> results = allQuery.getResultList();
        if (results.isEmpty()) {
            throw new RuntimeException("В БД нет ни одной записи");
        }
        return results;
    }

    @Override
    @Transactional
    public T save(T model) {
        entityManager.persist(model);

        return model;
    }

    @Override
    @Transactional
    public T update(T model) {
        model = entityManager.merge(model);

        return model;
    }

    @Override
    @Transactional
    public T remove(T model) {
        entityManager.remove(model);

        return model;
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getPersistentClass() {
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), Dao.class);
    }
}
