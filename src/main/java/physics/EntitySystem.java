package physics;

import core.entity.EntityContainer;

public abstract class EntitySystem {

    private EntityContainer entities;

    public EntitySystem(EntityContainer entities) {
        this.entities = entities;
    }

    public EntityContainer getEntities() {return entities;}

    public abstract void update(long delta);
}
