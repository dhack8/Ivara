package maths;

import processing.core.PVector;

/**
 * Class to store 2D vectors with floats.
 *
 * @author David Hack
 */
public class Vector extends PVector{

    /**
     * Creates a new Vector class from x y.
     * @param x x location
     * @param y y location
     */
    public Vector(float x, float y){
        super(x,y);
    }


    /**
     * Creates a new Vector class from another.
     * @param other another vector
     */
    public Vector(Vector other){
        super(other.x, other.y);
    }
}
