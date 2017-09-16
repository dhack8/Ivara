package core.components;

import core.scene.Entity;
import maths.Vector;
import physics.Collider;

/**
 * Created by Callum Li on 9/15/17.
 */
public class ColliderComponent extends Component {

    private Vector transform = new Vector(0, 0);
    private final Collider collider;

    public ColliderComponent(Entity entity, Collider collider) {
        super(entity);
        this.collider = collider;
    }

    /**
     * Method to get the collider object.
     * @return the collider
     */
    public Collider getCollider() {
        return collider.translate(getEntity().getPosition());
    }

    /**
     * Method to get the transform vector.
     * @return the transform vector.
     */
    public Vector getTransform() {
        return transform;
    }
}
