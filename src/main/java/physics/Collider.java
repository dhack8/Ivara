package physics;

import maths.Vector;

/**
 * Created by Callum Li on 9/15/17.
 */
public abstract class Collider {

    public abstract Collider translate(Vector vector);

    public abstract AABBCollider getAABBBoundingBox();
}
