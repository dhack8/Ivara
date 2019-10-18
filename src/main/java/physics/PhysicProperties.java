package physics;

import maths.Vector;

import java.io.Serializable;

/**
 * Class for story physics properties.
 * @author Callum Li
 */
public class PhysicProperties implements Serializable {

    /**
     * The canonical default physics property.
     */
    public static final PhysicProperties DEFAULT = new PhysicProperties();

    /**
     * Collider types.
     */
    public enum Type {
        STATIC, DYNAMIC;
    }

    public Type type = Type.STATIC;
    public float inverseMass = 0;

    /**
     * Constructs a physics body describer with infinite
     * mass and as a static collider.
     */
    private PhysicProperties() {
    }

    /**
     * Constructs a physics body describer with the given
     * mass and as a static collider.
     * @param mass The desired mass.
     */
    public PhysicProperties(float mass) {
        this.inverseMass = 1/mass;
    }

    /**
     * Constructs a physics body describer with the given
     * mass and collider type.
     * @param mass The desired mass.
     * @param type The desired collider type.
     */
    public PhysicProperties(float mass, Type type) {
        this.inverseMass = 1/mass;
        this.type = type;
    }

    /**
     * Returns the inverse mass.
     * @return The inverse mass.
     */
    public float getInverseMass() {
        return inverseMass;
    }

    /**
     * Returns the collider type.
     * @return The collider type.
     */
    public Type getType() {
        return type;
    }
}
