package core.struct;

import maths.Vector;

/**
 * Represents a camera by data with top left
 * @author David Hack
 */
public class Camera {
    public final Vector transform;
    public final Vector dimensions;

    public Camera(Vector transform, Vector dimensions) {
        this.transform = transform;
        this.dimensions = dimensions;
    }
}
