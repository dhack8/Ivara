package physics;

import maths.Vector;

/**
 * A class that describes an AABB Collider.
 * @author Callum Li
 */
public class AABBCollider extends Collider {
    public static final int CENTER_RADIUS = 1;
    public static final int MIN_DIM = 2;

    final Vector center;
    final Vector radius;

    /**
     * Creates an AABBCollider from two given vectors. The first is the
     * location of the center, and the second the half-width and half-height.
     *
     * @param center The position of the AABB's center.
     * @param radius The half-width and half-height.
     */
    public AABBCollider(Vector center, Vector radius) {
        this.center = new Vector(center);
        this.radius = new Vector(radius);
    }

    /**
     * Creates an AABB Collider from the given arguments. The
     * way they are treated depends on the given mode.
     *
     * Valid modes: CENTER_RADIUS, MIN_DIM.
     * @param mode The constructor mode.
     * @param v1 The first paremeter for the constructor.
     * @param v2 The second paremeter for the constructor.
     */
    public AABBCollider(int mode, Vector v1, Vector v2) {
        if (mode == CENTER_RADIUS) {
            center = new Vector(v1);
            radius = new Vector(v2);
        } else //if (mode == MIN_DIM)
        {
            center = new Vector(v1.x + (v2.x/2), v1.y + (v2.y/2));
            radius = new Vector(v2.x/2, v2.y/2);
        }
    }

    /**
     * Gets a new AABBCollider created from a vector with the values passed in, along with the radius.
     * @param vector vector to be translated
     * @return new, translated, AABBCollider
     */
    @Override
    public Collider translate(Vector vector) {
        return new AABBCollider(new Vector(center.x + vector.x, center.y + vector.y), radius);
    }


    @Override
    public AABBCollider getAABBBoundingBox() {
        return this;
    }

    /**
     * Returns the position of the corner with the smallest x and y values.
     * @return The position of the lowest corner.
     */
    public Vector getMin() {
        return new Vector(center.x - radius.x, center.y - radius.y);
    }

    /**
     * Returns the position of the corner with the largest x and y value.
     * @return The position of the largest corner.
     */
    public Vector getMax() {
        return new Vector(center.x + radius.x, center.y + radius.y);
    }

    /**
     * Returns the dimensions (width and height) of the AABBCollider.
     * @return The dimensions of the AABBCollider.
     */
    public Vector getDimension() {
        return new Vector(radius.x*2, radius.y*2);
    }

}
