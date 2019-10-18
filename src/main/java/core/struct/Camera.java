package core.struct;

import maths.Vector;

import java.io.Serializable;

/**
 * Represents a camera that the renderer uses to render the game
 * If the aspect ratio is incorrect for the screen scaling will remain square.
 * And black bars will be used, it is recommended to run with the correct aspect ratio.
 * @author David Hack
 */
public class Camera implements Serializable {

    //Make this 43 for 21:9 aspect ratio, yes 21:9 is actually 21.5:9
    public final static float width = 32;
    public final static float height = 18;

    //Transform from origin
    public final Vector transform;
    //Camera dimensions
    public final Vector dimensions;

    /**
     * Constructs a camera with a transform and dimensions.
     * @param transform transform from origin
     * @param dimensions camera dimensions
     */
    public Camera(Vector transform, Vector dimensions) {
        this.transform = transform;
        this.dimensions = dimensions;
    }

    /**
     * Default camera constructor uses width and height constants.
     */
    public Camera(){
        this.transform = new Vector(0,0);
        this.dimensions = new Vector(width, height);
    }
}
