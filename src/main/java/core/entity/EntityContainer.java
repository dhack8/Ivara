package core.entity;

import core.components.Component;
import core.scene.Entity;

import java.util.Collection;
import java.util.HashSet;

/**
 * Used to contain multiple entities.
 * Assumes entities don't share components.
 *
 * @author Will Pearson
 */
public class EntityContainer {

    Collection<Entity> entities = new HashSet<>();

    public boolean addEntity(Entity entity) {
        return entities.add(entity);
    }

    /**
     * Retrieves all components in the container.
     * @return
     */
    public Collection<Component> getAllComponents() {
        Collection<Component> components = new HashSet<>();
        for (Entity entity : entities)
            for (Component component : entity.getComponents())
                components.add(component);
        return components;
    }
}
