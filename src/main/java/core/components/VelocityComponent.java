package core.components;

import core.entity.Entity;
import maths.Vector;

/**
 * Holds the velocity of an Entity.
 *
 * @author Will Pearson
 */
public class VelocityComponent extends Component {

    private Vector velocity = new Vector(0,0);

    public VelocityComponent(Entity entity) {
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
