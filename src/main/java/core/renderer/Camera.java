package core.renderer;

import maths.Vector;

/**
 * A class that stores information on the camera location, dimensions, and scale.
 *
 * @author David Hack
 */
public class Camera {
    private Vector location;
    private Vector dimension;
    private double scale;

    /**
     * Creates a new camera with just the dimension of the screen, default location 0,0 and scale 1.0.
     * @param dimension dimensions of current screen.
     */
    public Camera(Vector dimension){
        this.location = new Vector(0,0);
        this.dimension = dimension;
        scale = 1.0;
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
    public void setScale(double s){
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
    public double getScale(){
        return scale;
    }
}
