package physics;

import maths.Vector;

/**
 *
 */
public class PhysicProperties {

    public static final PhysicProperties DEFAULT = new PhysicProperties();

    public enum Type {
        STATIC, DYNAMIC;
    }

    public Type type = Type.STATIC;
    public float inverseMass = 0;
    private Vector netForce = new Vector(0, 0);

    private PhysicProperties() {
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
