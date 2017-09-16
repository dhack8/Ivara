package core.components;

import core.entity.Entity;

/**
 * Created by Callum Li on 9/15/17.
 */
public abstract class Component {
    private final Entity entity;

    public Component(Entity entity) {
        this.entity = entity;
    }

    public final Entity getEntity() {
        return entity;
    }

    public void update(long delta) {}
}
