package miron.app.dao.filtering.strategies;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class FilteringById extends FilteringStrategy {

    @Override
    public String getStrategyName() { return "id"; }

    @Override
    public Criterion getCriterion(String propertyName, String value) {
        return Restrictions.eq(propertyName, Integer.parseInt(value));
    }
}
