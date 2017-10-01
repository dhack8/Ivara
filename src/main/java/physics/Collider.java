package physics;

import maths.Vector;

/**
 * An abstract class that describes the requirements for a Collider.
 *
 * An implementation of Collider also requires an extension of
 * the CollisionUtil.
 * @see CollisionUtil
 */
public abstract class Collider {

    /**
     * Creates a new collider translated by the given vector.
     * @param vector The translation vector.
     * @return The collider translated.
     */
    public abstract Collider translate(Vector vector);

    /**
     * Returns an AABBCollider that completely contains this collider.
     * @return An AABBCollider that completely contains this collider.
     */
    public abstract AABBCollider getAABBBoundingBox();
}
