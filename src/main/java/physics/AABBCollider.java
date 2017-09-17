package physics;

import maths.Vector;

/**
 * An AABBCollider .
 */
public class AABBCollider extends Collider {
    public static final int TOPLEFT = 2;

    Vector center;
    Vector radius;

    /**
     * Creates an AABBCollider from two given vectors. The first is the
     * location of the center, and the second the half-width and half-height.
     *
     * @param center The position of the AABB's center.
     * @param radius The half-width and half-height.
     */
    public AABBCollider(Vector center, Vector radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Creates and AABBCollider from an int and two given vectors. TODO finish
     * @param type
     * @param v1
     * @param v2
     */
    public AABBCollider(int type, Vector v1, Vector v2) {
        if (type == TOPLEFT) {
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

    /**
     * Gets the AABBCollider
     * @return this AABBCollider
     */
    @Override
    public AABBCollider getAABBBoundingBox() {
        return this;
    }

    /**
     * Gets vector of minimum values
     * @return vector based off minimum values
     */
    public Vector getMin() {
        return new Vector(center.x - radius.x, center.y - radius.y);
    }

    /**
     * Gets vector of maximum values
     * @return vector based off maximum values
     */
    public Vector getMax() {
        return new Vector(center.x + radius.x, center.y + radius.y);
    }

    /**
     * Gets the dimensions (width and height) of the AABBCollider.
     * @return vector symbolising the bounding box of the AABBCollider
     */
    public Vector getDimension() {
        return new Vector(radius.x*2, radius.y*2);
    }

}
