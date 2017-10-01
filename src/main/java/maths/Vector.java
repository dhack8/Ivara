package maths;

import processing.core.PVector;

/**
 * Class to store 2D vectors with floats.
 *
 * @author David Hack
 */
public class Vector {

    public float x;
    public float y;

    /**
     * Creates a new Vector class from x y.
     * @param x x location
     * @param y y location
     */
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void add(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public void add(Vector vector) {
        add(vector.x, vector.y);
    }

    public void scale(float s) {
        this.x *= s;
        this.y *= s;
    }

    /**
     * Creates a new Vector class from another.
     * @param other another vector
     */
    public Vector(Vector other){
        this.x = other.x;
        this.y = other.y;
    }


        @Override
        public String toString() {
            return "Vector {" + x + ", " + y + "}";
        }
    }
