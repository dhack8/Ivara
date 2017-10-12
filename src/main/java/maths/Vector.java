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
