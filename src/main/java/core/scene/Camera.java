package core.scene;

import backends.processing.PWindow;
import maths.Vector;

/**
 * A class that stores information on the camera location, dimensions, and scale.
 *
 * @author David Hack
 */
public class Camera {


    private Vector location;
    private Vector dimension;
    private float scale;

    /**
     * Creates a new camera, default location 0,0 and scale 1.0.
     */
    public Camera(){
        //Z value doesn't matter for camera
        this.location = new Vector(0,0);
        this.dimension = new Vector(PWindow.intWidth, PWindow.intHeight);
        scale = 100.0f;
    }

    public Camera(Vector location, Vector dimension, float scale) {
        this.location = location;
        this.dimension = dimension;
        this.scale = scale;
    }

    /**
     * Moves the camera from its current location.
     * @param dx delta x
     * @param dy delta y
     */
    public void move(float dx, float dy){
        location.add(dx, dy);
    }

    /**
     * Sets the camera dimensions.
     * @param x x dimension
     * @param y y dimension
     */
    public void setDimension(float x, float y){
        dimension = new Vector(x, y);
    }

    /**
     * Sets the camera scale.
     * @param s scale
     */
    public void setScale(float s){
        scale = s;
    }

    /**
     * Getter for the location.
     * @return location of camera
     */
    public Vector getLocation(){
        return new Vector(location);
    }

    /**
     * Getter for the camera dimension.
     * @return dimension of camera
     */
    public Vector getDimension(){
        return new Vector(dimension);
    }

    /**
     * Getter for the camera scale.
     * @return scale of camera
     */
    public float getScale(){
        return scale;
    }
}
