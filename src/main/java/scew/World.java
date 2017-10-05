package scew;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A world is essentially a container for entities and systems.
 *
 * @param <T> The type of entity this world contains.
 */
public class World<T extends Entity> {

    /**
     * The backing collection for the entities.
     */
    private final Collection<T> entities = new ArrayList<>();

    /**
     * The backing collection for the systems.
     */
    private final List<System<T>> systems = new ArrayList<>();

    /**
     * Returns all the entities within the world.
     * @return All the entities within the world.
     */
    final public Collection<T> getEntities() {
        return entities;
    }

    /**
     * Adds the given entity to the world.
     * @param entity The entity to add to the world.
     */
    final public void addEntity(T entity) {
        entities.add(entity);
    }

    /**
     * Removes the given entity from the world if it exists in this world.
     * @param entity The entity to remove.
     * @return True if an entity was removed, otherwise false.
     */
    final public boolean removeEntity(T entity) {
        return entities.remove(entity);
    }

    /**
     * Adds the given system to the world.
     * @param system The system to add.
     */
    final public void addSystem(System<T> system) {
        systems.add(system);
    }

    /**
     * Returns all the components of the given type in the world.
     * @param type The type of the components.
     * @param <U> The type of the components.
     * @return A collection of all the components of the given type in the world.
     */
    final public <U extends Component<T>> Collection<U> get(Class<U> type) {

        // todo: optimise
        return entities.stream()
                .flatMap((e) -> e.getComponents().stream())
                .filter((c) -> c.getClass().equals(type))
                .map((c) -> type.cast(c))
                .collect(Collectors.toSet());
    }

    /**
     * Updates the world by the given amount of milliseconds
     * @param dt The amount of milliseconds to update by.
     */
    final public void update(int dt) {
        for (System<T> system : systems) {
            system.update(dt, this);
        }
    }
}
