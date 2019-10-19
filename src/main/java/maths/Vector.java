package maths;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class to store 2D vectors with floats.
 *
 * @author David Hack
 */
public class Vector implements Serializable {

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

    /**
     * Creates a new Vector class from another.
     * @param other another vector
     */
    public Vector(Vector other){
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * Sets this vector to the provided one.
     * @param vector vector to set to
     */
    public void setAs(Vector vector) {
        setAs(vector.x, vector.y);
    }

    /**
     * Sets this vector to the provided values.
     * @param x value
     * @param y value
     */
    public void setAs(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Increments this vector by anothers values.
     * @param vector vector to increment by
     */
    public void incrementBy(Vector vector) {
        incrementBy(vector.x, vector.y);
    }

    /**
     * Increments this vector by the provided values.
     * @param dx change in x
     * @param dy change in y
     */
    public void incrementBy(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Scales this vector be the provided value
     * @param s scale factor
     */
    public void scaleBy(float s) {
        this.x *= s;
        this.y *= s;
    }

    /**
     * Returns the distance between two vectors without modifying them.
     * @param other the other vector to measure between
     * @return distance between the two vectors
     */
    public float dist(Vector other){
        return (float) Math.sqrt((x-other.x)*(x-other.x) + (y-other.y)*(y-other.y));
    }

    /**
     * Returns the magnitude of this vector without modifying it.
     * @return magnitude ie length of vector
     */
    public float magnitude(){
        return (float) Math.sqrt(x*x + y*y);
    }

    /**
     * Returns a vector in normalized form of this one, without modifying it.
     * @return normalized vector
     */
    public Vector norm(){
        float m = magnitude();
        return new Vector(x/m, y/m);
    }

    /**
     * Adds this and the other vector together without modifying them.
     * @param other other vector to add
     * @return sum of the two vectors
     */
    public Vector add(Vector other){
        return new Vector(x+other.x, y+other.y);
    }

    /**
     * Subtracts this and the other vector together without modifying them.
     * ORDER MATTERS
     * @param other other vector to sub
     * @return sub of the two vectors
     */
    public Vector sub(Vector other){
        return new Vector(x-other.x, y-other.y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof  Vector)) return false;

        Vector other = (Vector) obj;
        return other.x == this.x && other.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Vector {" + x + ", " + y + "}";
    }
}
