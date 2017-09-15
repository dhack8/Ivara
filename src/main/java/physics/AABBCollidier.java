package physics;

import maths.Vector;

/**
 * Created by Callum Li on 9/15/17.
 */
public class AABBCollidier extends Collidier {

    Vector center;
    Vector radius;

    public AABBCollidier(Vector center, Vector radius) {
        this.center = center;
        this.radius = radius;
    }
}
