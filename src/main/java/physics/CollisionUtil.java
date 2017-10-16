package physics;

import maths.Vector;

/**
 * A class that contains useful static methods for detecting and resolving
 * collisions.
 * @author Cullum Li
 */
public class CollisionUtil {

    /**
     * Determines whether the given colliders are intersecting.
     * @param c1 The first collider
     * @param c2 The second collider
     * @return True if they are intersecting, otherwise false.
     */
    public static boolean intersect(Collider c1, Collider c2) {
        if (c1 instanceof AABBCollider &&
                c2 instanceof AABBCollider) {

            return intersect((AABBCollider) c1, (AABBCollider) c2);
        }
        return false;
    }

    /**
     * Returns true if the given point is contained within the
     * given collider.
     * @param collider The collider.
     * @param point The point.
     * @return True if the point is within the collider, otherwise false.
     */
    public static boolean contains(Collider collider, Vector point) {
        if (collider instanceof AABBCollider) {
            return contains((AABBCollider) collider, point);
        }
        return false;
    }

    /**
     * Returns true if the given point is contained within the
     * given AABB Collider.
     * @param collider The AABB Collider.
     * @param point The point.
     * @return True if the point is within the AABB Collider, otherwise false.
     */
    public static boolean contains(AABBCollider collider, Vector point) {
        Vector min = collider.getMin();
        Vector max = collider.getMax();
        return point.x >= min.x && point.y >= min.y && point.x <= max.x && point.y <= max.y;
    }

    /**
     * Returns whether two given AABBCollidiers are intersecting.
     * @param a The first AABBCollider.
     * @param b The second AABBCollider.
     * @return True if they are intersecting, false otherwise.
     */
    public static boolean intersect(AABBCollider a, AABBCollider b) {

        if (Math.abs(a.center.x - b.center.x) > (a.radius.x + b.radius.x)) {
            return false;
        }
        if (Math.abs(a.center.y - b.center.y) > (a.radius.y + b.radius.y)) {
            return false;
        }
        return true;
    }

    /**
     * Returns the minimum distance vector to separate the two
     * Axis-Aligned Bounding-Box Colliders.
     *
     * @param a The first AABBCollider.
     * @param b The second AABBCollider.
     * @return The minimum distance vector.
     */
    public static Vector minimumDistanceVector(AABBCollider a, AABBCollider b) {
        Vector horiVec = minimumHorizontalVector(a, b);
        Vector vertVec = minimumVerticalVector(a, b);
        return (Math.abs(horiVec.x) > Math.abs(vertVec.y)) ? vertVec : horiVec;
    }

    /**
     * Returns the minimum HORIZONTAL distance vector to separate the two
     * Axis-Aligned Bounding-Box Colliders.
     *
     * @param a The first AABBCollider.
     * @param b The second AABBCollider.
     * @return The minimum distance vector.
     */
    static Vector minimumHorizontalVector(AABBCollider a, AABBCollider b) {
        float dx;
        if (a.center.x < b.center.x)
            dx = a.center.x + a.radius.x - b.center.x + b.radius.x;
        else
            dx = a.center.x - a.radius.x - b.center.x - b.radius.x;
        return new Vector(dx, 0f);
    }

    /**
     * Returns the minimum VERTICAL distance vector to separate the two
     * Axis-Aligned Bounding-Box Colliders.
     *
     * @param a The first AABBCollider.
     * @param b The second AABBCollider.
     * @return The minimum distance vector.
     */
    static Vector minimumVerticalVector(AABBCollider a, AABBCollider b) {
        float dy;
        if (a.center.y < b.center.y)
            dy = a.center.y + a.radius.y - b.center.y + b.radius.y;
        else
            dy = a.center.y - a.radius.y - b.center.y - b.radius.y;
        return new Vector(0f, dy);
    }
}
