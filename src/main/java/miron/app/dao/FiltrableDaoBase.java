package miron.app.dao;

import miron.app.dao.filtering.strategies.FilteringStrategy;
import miron.app.model.PersistantModel;
import miron.app.view.BootgridTableRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class FiltrableDaoBase<T extends PersistantModel> extends Dao<T> implements FiltrableDao<T> {
    @Autowired
    private List<FilteringStrategy> strategies;

    @Override
    @Transactional
    public List<T> getAllByTableRequest(BootgridTableRequest gridRequest) {
        Criteria criteria = buildFilteringCriteria(gridRequest.getFilters(), createCriteria());

        addSorts(gridRequest, criteria);

        int rows = gridRequest.getRowCount();
        criteria.setFirstResult((gridRequest.getCurrent() -1) * rows);
        criteria.setMaxResults(rows);

        return criteria.list();
    }

    @Override
    @Transactional
    public int getTotalRowCount(BootgridTableRequest gridRequest) {
        Criteria criteria = buildFilteringCriteria(gridRequest.getFilters(), createCriteria());

        Number count = (Number) criteria.setProjection(Projections.count("id")).uniqueResult();
        return count.intValue();
    }

    public static void addSorts(BootgridTableRequest gridRequest, Criteria criteria) {
        if (gridRequest.getSorts() != null) {
            gridRequest.getSorts().forEach((name, value) -> {
                boolean asc = "asc".equals(value);
                if (value != null && !value.isEmpty()) criteria.addOrder(asc ? Order.asc(name) : Order.desc(name));
            });
        }
    }

    public Criteria buildFilteringCriteria(Map<String, String> filters, Criteria criteria) {
        if (filters != null) {
            for (Map.Entry<String, String> filter : filters.entrySet()) {
                String value = filter.getValue();
                if (value != null && !value.isEmpty()) {
                    Optional<FilteringStrategy> matching = strategies.stream()
                            .filter(strategy -> strategy.getStrategyName()
                                .equals(filter.getKey()))
                            .findFirst();

                    if (matching.isPresent()) {
                        FilteringStrategy strategy = matching.get();
                        String propertyName = strategy.getStrategyName();
                        criteria.add(strategy.getCriterion(propertyName, value));
                    } else {
                        throw new RuntimeException("Запрашиваемый фильтр "
                                + filter.getKey() + " не имеет стратегию "
                                + filter.getValue()
                        );
                    }
                }
            }
        }
        return criteria;
    }

    protected Criteria createCriteria() {
        Session session = entityManager.unwrap(Session.class);
        return session.createCriteria(getPersistentClass());
    }
}
