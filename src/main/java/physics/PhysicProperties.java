package physics;

import maths.Vector;

/**
 * Created by Callum Li on 9/17/17.
 */
public class PhysicProperties {
    public enum Type {
        STATIC, DYNAMIC;
    }

    private Type type = Type.STATIC;
    private float inverseMass = 0;
    private Vector netForce = new Vector(0, 0);

    public PhysicProperties() {
    }

    public PhysicProperties(float mass) {
        this.inverseMass = 1/mass;
    }

    public PhysicProperties(float mass, Type type) {
        this.inverseMass = 1/mass;
        this.type = type;
    }

    public float getInverseMass() {
        return inverseMass;
    }

    public Type getType() {
        return type;
    }
}
