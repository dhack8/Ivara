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

    public Collider getCollider() {
        return collider.translate(getEntity().getPosition());
    }

    public Vector getTransform() {
        return transform;
    }
}
