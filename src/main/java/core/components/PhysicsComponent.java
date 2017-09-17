package core.components;

import core.entity.Entity;
import maths.Vector;

/**
 * Created by Callum Li on 9/16/17.
 */
public class PhysicsComponent extends Component {

    private float inverseMass = 0;
    private Vector netForce = new Vector(0, 0);

    public PhysicsComponent(Entity entity) {
        super(entity);
    }

    /**
     * Creates a physics component with a mass value
     * @param entity parent entity
     * @param mass float value of mass
     */
    public PhysicsComponent(Entity entity, float mass) {
        super(entity);
        this.inverseMass = 1/mass;
    }

    public void applyForce(Vector force) {
        netForce.add(force);
    }


}
