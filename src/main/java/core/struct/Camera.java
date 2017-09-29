package core.struct;

import maths.Vector;

/**
 * Created by Callum Li on 9/29/17.
 */
public class Camera {
    public final Vector transform;
    public final Vector dimensions;

    public Camera(Vector transform, Vector dimensions) {
        this.transform = transform;
        this.dimensions = dimensions;
    }
}
