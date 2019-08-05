package miron.app.dao.filtering.strategies;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.stream.Stream;

public class StringRestrictions {
    public static String ESCAPE_CHAR = "\\";

    public static Criterion getByEntering(String propertyName, String value) {
        return getCriterion(propertyName, value, MatchMode.ANYWHERE);
    }

    private static Criterion getCriterion(String propertyName, String value, MatchMode mode) {
        value = value.replace("\\", ESCAPE_CHAR + "\\")
                .replace("_", ESCAPE_CHAR + "_")
                .replace("%", ESCAPE_CHAR + "%");
        return Stream.of(value.split("\\s"))
                .<Criterion>map(s -> Restrictions.like(propertyName, s, mode).ignoreCase())
                .reduce(Restrictions::and).get();
    }
}
