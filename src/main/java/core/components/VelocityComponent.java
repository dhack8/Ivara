package core.components;

import core.entity.GameEntity;
import scew.Component;
import maths.Vector;

/**
 * Holds the velocity of an GameEntity.
 *
 * @author Will Pearson
 */
public class VelocityComponent extends Component<GameEntity> {

    private final Vector velocity;

    public VelocityComponent(GameEntity entity) {
        super(entity);
        this.velocity = new Vector(0, 0);
    }

    public VelocityComponent(GameEntity entity, Vector velocity) {
        super(entity);
        this.velocity = new Vector(velocity.x, velocity.y);
    }

    /**
     * Changes the velocity by the set amount.
     * @param dx deltax
     * @param dy deltay
     */
    public void add(float dx, float dy) {
        velocity.add(dx, dy);
    }

    /**
     * Gets velocity.
     * @return velocity
     */
    public Vector getVelocity() {
        return velocity;
    }

    public void set(Vector velocity) {
        this.velocity.set(velocity);
    }

    public void setX(float x) { // Todo change how this is done :)
        velocity.set(x,velocity.y);
    }

    public void setY(float y) { // Todo change how this is done :)
        velocity.set(velocity.x,y);
    }
}
