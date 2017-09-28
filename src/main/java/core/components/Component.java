package core.components;

import core.entity.GameEntity;
import maths.Vector;

/**
 * Created by Callum Li on 9/15/17.
 */
public abstract class Component {
    private final GameEntity entity;

    public Component(GameEntity entity) {
        this.entity = entity;
    }

    public final GameEntity getEntity() {
        return entity;
    }

    public void update(long delta) {}

    protected Vector getPosition(){return entity.getPosition();}
}
