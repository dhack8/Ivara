package core.struct;

import maths.Vector;

/**
 * Created by Callum Li on 9/30/17.
 */
public class Sprite {

    public final Vector transform;
    public final Vector dimensions;
    public final String resourceID;


    public Sprite(Vector transform, Vector dimensions, String resourceID) {
        this.transform = transform;
        this.dimensions = dimensions;
        this.resourceID = resourceID;
    }
}
