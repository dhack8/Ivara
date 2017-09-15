package physics;

import maths.Vector;

/**
 * An AABBCollider .
 */
public class AABBCollider extends Collider {

    Vector center;
    Vector radius;

    /**
     * Creates an AABB from two given Vectors. The first is the
     * location of the center, and the second the half-width and half-height.
     *
     * @param center The position of the AABB's center.
     * @param radius The half-width and half-height.
     */
    public AABBCollider(Vector center, Vector radius) {
        this.center = center;
        this.radius = radius;

    }
}
