package physics;

import maths.Vector;

/**
 * An AABBCollider .
 */
public class AABBCollider extends Collider {

    private Vector center;
    private Vector radius;

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

    @Override
    public Collider translate(Vector vector) {
        return new AABBCollider(new Vector(center.x + vector.x, center.y + vector.y), radius);
    }

    
}
