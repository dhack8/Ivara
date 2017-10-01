package physics;

import maths.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CollisionUtil unit tests.
 * Assumes y axis is top down and x axis is left right.
 *
 * @author Will Pearson
 */
public class CollisionUtilTestA {
    @Test
    public void intersect() throws Exception {
    }

    @Test
    public void intersect1() throws Exception {
    }

    @Test
    public void minimumDistanceVector() throws Exception {
    }

    @Test
    public void minimumHorizontalVector() throws Exception {
    }

    @Test
    public void minimumVerticalVector() throws Exception {
    }

    /**
     * Tests whether two identical AABBCollidiers intersect.
     * @throws Exception
     */
    @Test
    public void intersectTest1() throws Exception {
        AABBCollider a = generateAABBCollider(0,0,10,10);
        AABBCollider b = generateAABBCollider(0,0,10,10);

        assertTrue(CollisionUtil.intersect(a,b));
    }

    /**
     * Tests corner intersection.
     * @throws Exception
     */
    @Test
    public void intersectTest2() throws Exception {
        AABBCollider a = generateAABBCollider(-5,-5,6,6);
        AABBCollider b = generateAABBCollider(5,5,5,5);

        assertTrue(CollisionUtil.intersect(a,b));
    }

    /**
     * Tests for no corner intersection.
     * @throws Exception
     */
    @Test
    public void intersectTest3() throws Exception {
        AABBCollider a = generateAABBCollider(-5,-5,4,4);
        AABBCollider b = generateAABBCollider(5,5,5,5);

        assertFalse(CollisionUtil.intersect(a,b));
    }

    /**
     * Tests for correct minimal horizontal vector when A intersects B.
     * A is on the left of B.
     */
    @Test
    public void minimalHorizontalVector1() {
        AABBCollider a = generateAABBCollider(0,0,5,5);
        AABBCollider b = generateAABBCollider(10,0,7,7);
        Vector expectedResult = new Vector(2,0);

        assertEquals(CollisionUtil.minimumHorizontalVector(a,b),expectedResult);
    }

    /**
     * Tests for correct minimal horizontal vector when A intersects B.
     * A is on the right of B.
     */
    @Test
    public void minimalHorizontalVector2() {
        AABBCollider b = generateAABBCollider(0,0,5,5);
        AABBCollider a = generateAABBCollider(10,0,7,7);
        Vector expectedResult = new Vector(-2,0);

        assertEquals(CollisionUtil.minimumHorizontalVector(a,b),expectedResult);
    }

    /**
     * Tests for correct minimal horizontal vector when A doesn't intersect B.
     * A is on the left of B.
     */
    @Test
    public void minimalHorizontalVector3() {
        AABBCollider a = generateAABBCollider(0,0,5,5);
        AABBCollider b = generateAABBCollider(10,0,3,3);
        Vector expectedResult = new Vector(-2,0);

        assertEquals(CollisionUtil.minimumHorizontalVector(a,b),expectedResult);
    }

    /**
     * Tests for correct minimal horizontal vector when A doesn't intersect B.
     * A is on the right of B.
     */
    @Test
    public void minimalHorizontalVector4() {
        AABBCollider b = generateAABBCollider(0,0,5,5);
        AABBCollider a = generateAABBCollider(10,0,3,3);
        Vector expectedResult = new Vector(2,0);

        assertEquals(CollisionUtil.minimumHorizontalVector(a,b),expectedResult);
    }

    /**
     * Tests for correct minimal vertical vector when A intersects B.
     * A is above B.
     */
    @Test
    public void minimalVerticalVector1() {
        AABBCollider a = generateAABBCollider(0,0,5,5);
        AABBCollider b = generateAABBCollider(0,10,7,7);
        Vector expectedResult = new Vector(0,2);

        assertEquals(CollisionUtil.minimumVerticalVector(a,b),expectedResult);
    }

    /**
     * Tests for correct minimal vertical vector when A intersects B.
     * A is below B.
     */
    @Test
    public void minimalVerticalVector2() {
        AABBCollider b = generateAABBCollider(0,0,5,5);
        AABBCollider a = generateAABBCollider(0,10,7,7);
        Vector expectedResult = new Vector(0,-2);

        assertEquals(CollisionUtil.minimumVerticalVector(a,b),expectedResult);
    }

    /**
     * Tests for correct minimal vertical vector when A doesn't intersect B.
     * A is above B.
     */
    @Test
    public void minimalVerticalVector3() {
        AABBCollider a = generateAABBCollider(0,0,5,5);
        AABBCollider b = generateAABBCollider(0,10,3,3);
        Vector expectedResult = new Vector(0,-2);

        assertEquals(CollisionUtil.minimumVerticalVector(a,b),expectedResult);
    }

    /**
     * Tests for correct minimal Vertical vector when A doesn't intersect B.
     * A is below B.
     */
    @Test
    public void minimalVerticalVector4() {
        AABBCollider b = generateAABBCollider(0,0,5,5);
        AABBCollider a = generateAABBCollider(0,10,3,3);
        Vector expectedResult = new Vector(0,2);

        assertEquals(CollisionUtil.minimumVerticalVector(a,b),expectedResult);
    }

    /**
     * Tests for the correct minimal distance vector when a change in
     * x is smaller than a change in y.
     */
    @Test
    public void minimalDistanceVector1() {
        AABBCollider a = generateAABBCollider(0,0,5,5);
        AABBCollider b = generateAABBCollider(9,7,5,5);
        Vector expectedResult = new Vector(1,0);

        assertEquals(CollisionUtil.minimumDistanceVector(a,b),expectedResult);
    }

    /**
     * Tests for the correct minimal distance vector when a change in
     * x is larger than a change in y.
     */
    @Test
    public void minimalDistanceVector2() {
        AABBCollider a = generateAABBCollider(0,0,5,5);
        AABBCollider b = generateAABBCollider(7,9,5,5);
        Vector expectedResult = new Vector(0,1);

        assertEquals(CollisionUtil.minimumDistanceVector(a,b),expectedResult);
    }

    //------------- Helper methods ------------------------------

    /**
     * Generates an AABBCollider.
     * @param cx centre x
     * @param cy centre y
     * @param rx radius x
     * @param ry radius y
     * @return The AABBCollider
     */
    private AABBCollider generateAABBCollider(float cx, float cy, float rx, float ry) {
        return new AABBCollider(new Vector(cx, cy), new Vector(rx, ry));
    }

}