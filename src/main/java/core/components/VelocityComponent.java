package core.components;

import core.entity.GameEntity;
import eem.Component;
import maths.Vector;

/**
 * Holds the velocity of an GameEntity.
 *
 * @author Will Pearson
 */
public class VelocityComponent extends Component<GameEntity> {

    private Vector velocity = new Vector(0,0);

    public VelocityComponent(GameEntity entity) {
        super(entity);
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
}
