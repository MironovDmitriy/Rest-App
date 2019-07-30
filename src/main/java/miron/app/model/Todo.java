package miron.app.model;

import lombok.Getter;
import lombok.Setter;
import miron.app.model.PersistantModel;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Todo extends PersistantModel {
    private String description;

    @Override
    public String toString() {
        return "id " + getId() + " desc: " + getDescription();
    }
}
