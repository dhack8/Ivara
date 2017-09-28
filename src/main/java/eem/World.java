package eem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class World {

    private final Collection<Entity> entities = new HashSet<>();
    private final List<System> systems = new ArrayList<>();

    final private void addEntity(Entity entity) {
        entities.add(entity);
    }

    final private void addSystem(SystemConstructor systemConstructor) {
        systems.add(systemConstructor.create(this));
    }

    final private void update(int dt) {
        for (System system : systems) {
            system.update(dt);
        }
    }
}
