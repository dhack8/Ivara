package physics;

import maths.Vector;

/**
 * An AABBCollider .
 */
public class AABBCollider extends Collider {
    public static final int TOPLEFT = 2;

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

    public AABBCollider(int type, Vector v1, Vector v2) {
        if (type == TOPLEFT) {
            center = new Vector(v1.x - (v2.x/2), v1.y - (v2.y/2));
            radius = new Vector(v2.x/2, v2.y/2);
        }
    }

    @Override
    public Collider translate(Vector vector) {
        return new AABBCollider(new Vector(center.x + vector.x, center.y + vector.y), radius);
    }

    
}
