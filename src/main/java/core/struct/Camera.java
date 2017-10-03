package core.struct;

import maths.Vector;

/**
 * Represents a camera by data with top left
 * @author David Hack
 */
public class Camera {

    public final static float width = 32;
    public final static float height = 18;

    public final Vector transform;
    public final Vector dimensions;

    public Camera(Vector transform, Vector dimensions) {
        this.transform = transform;
        this.dimensions = dimensions;
    }

    public Camera(){
        this.transform = new Vector(0,0);
        this.dimensions = new Vector(width, height);
    }
}
