package physics;

import core.entity.EntityContainer;

public abstract class CollisionResolver {

    protected EntityContainer entities;

    public CollisionResolver(EntityContainer entities) {
        this.entities = entities;
    }

    public void update(long delta) {
        resolveCollisions();
    }

    public abstract void resolveCollisions();
}
