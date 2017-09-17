package core.entity;

import core.components.Component;
import util.ClassMap;

import java.util.*;

/**
 * Used to contain multiple entities.
 * Assumes entities don't share components.
 *
 * @author Will Pearson
 */
public class EntityContainer implements Iterable<Entity> {

    Set<Entity> entities = new HashSet<>();
    ClassMap classMap = new ClassMap();

    public void addEntity(Entity entity) {
        entities.add(entity);
        for (Component component : entity.getComponents())
            classMap.put(component);


    }

    /**
     * Gets entities.
     * @return entites
     */
    public Collection<Entity> getEntities() {
        return entities;
    }

    /**
     * Retrieves all components in the container of the type clazz.
     * @param clazz the class type
     * @return components of type class
     */
    public <T extends Component> Collection<T> getAllComponents(Class<T> clazz) {
        return classMap.get(clazz);
    }

    @Override
    public Iterator<Entity> iterator() {
        return entities.iterator();
    }
}
