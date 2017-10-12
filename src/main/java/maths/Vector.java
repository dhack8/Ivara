package maths;

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

    public void set(Vector vector) {
        set(vector.x, vector.y);
    }

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void incrementBy(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public void incrementBy(Vector vector) {
        incrementBy(vector.x, vector.y);
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
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector other = (Vector) obj;
            return other.x == this.x && other.y == this.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vector {" + x + ", " + y + "}";
    }

    public float dist(Vector other){
        return (float) Math.sqrt((x-other.x)*(x-other.x) + (y-other.y)*(y-other.y));
    }
}
