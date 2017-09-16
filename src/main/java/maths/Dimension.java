package maths;

import processing.core.PVector;

/**
 * Class to store 2D dimensions with floats.
 *
 * @author David Hack
 */
public class Dimension extends PVector{

    /**
     * Creates a new Dimension class from x y.
     * @param x x location
     * @param y y location
     */
    public Dimension(float x, float y){
        super(x,y);
    }

    /**
     * Creates a new Dimension class from another.
     * @param other another vector
     */
    public Dimension(Dimension other){
        super(other.x, other.y);
    }
}

