package physics;

/**
 * Created by Callum Li on 9/15/17.
 */
public class Collision {

    /**
     * Returns whether two given AABBCollidiers are intersecting.
     * @param a The first AABBCollidier.
     * @param b The second AABBCollidier.
     * @return True if they are intersecting, false otherwise.
     */
    public static boolean intersect(AABBCollidier a, AABBCollidier b) {

        if (Math.abs(a.center.x - b.center.x) > (a.radius.x + b.radius.x)) {
            return false;
        }
        if (Math.abs(a.center.y - b.center.y) > (a.radius.y + b.radius.y)) {
            return false;
        }
        return true;
    }
}
