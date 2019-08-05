package miron.app.dao.filtering.strategies;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class FilteringByDescription extends FilteringStrategy {

    @Override
    public String getStrategyName() { return "description"; }

    @Override
    public Criterion getCriterion(String propertyName, String value) {
        return StringRestrictions.getByEntering(propertyName, value);
    }
}
