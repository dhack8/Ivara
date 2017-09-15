package physics;

/**
 * Created by Callum Li on 9/15/17.
 */
public class CollisionUtil {



    /**
     * Returns whether two given AABBCollidiers are intersecting.
     * @param a The first AABBCollider.
     * @param b The second AABBCollider.
     * @return True if they are intersecting, false otherwise.
     */
    static boolean intersect(AABBCollider a, AABBCollider b) {

        if (Math.abs(a.center.x - b.center.x) > (a.radius.x + b.radius.x)) {
            return false;
        }
        if (Math.abs(a.center.y - b.center.y) > (a.radius.y + b.radius.y)) {
            return false;
        }
        return true;
    }
}
