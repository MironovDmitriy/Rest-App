package miron.app.dao.filtering.strategies;

import org.hibernate.criterion.Criterion;

public abstract class FilteringStrategy {

    public abstract String getStrategyName();

    public abstract Criterion getCriterion(String propertyName, String value);
}
