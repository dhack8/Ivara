package eem;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


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
     *
     * @param system
     */
    final public void addSystem(System<T> system) {
        systems.add(system);
    }

    final public <U extends Component<T>> Collection<U> get(Class<U> type) {
        // todo: optimise
        return entities.stream()
                .flatMap((e) -> e.getComponents().stream())
                .filter((c) -> c.getClass().equals(type))
                .map((c) -> type.cast(c))
                .collect(Collectors.toSet());
    }

    /**
     *
     * @param dt
     */
    final public void update(int dt) {
        for (System<T> system : systems) {
            system.update(dt, this);
        }
    }
}
