package eem;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A world is essentially a container for entities.
 * @param <T> The type of entity this world contains.
 */
public class World<T extends Entity> {

    private final Collection<T> entities = new HashSet<>();
    private final ClassToInstanceMap<Component<T>> componentMap = MutableClassToInstanceMap.create();
    private final List<System<T>> systems = new ArrayList<>();

    final public Collection<T> getEntities() {
        return entities;
    }

    final public void addEntity(T entity) {
        entities.add(entity);
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
