package physics;

import maths.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Callum Li on 9/15/17.
 */
public class CollisionUtilTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
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
     * Tests for correct minimal horizontal vector when A intersects B.
     * A is on the left of B.
     */
    @Test
    public void minimalVerticalVector1() {
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
    public void minimalVerticalVector2() {
        AABBCollider b = generateAABBCollider(0,0,5,5);
        AABBCollider a = generateAABBCollider(10,0,7,7);
        Vector expectedResult = new Vector(-2,0);

        assertEquals(CollisionUtil.minimumHorizontalVector(a,b),expectedResult);
    }

    /**
     * Tests for correct minimal vertical vector when A doesn't intersect B.
     * A is above B.
     */
    @Test
    public void minimalVerticalVector3() {
        AABBCollider a = generateAABBCollider(0,0,5,5);
        AABBCollider b = generateAABBCollider(10,0,3,3);
        Vector expectedResult = new Vector(0,2);

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
        Vector expectedResult = new Vector(0,-2);

        assertEquals(CollisionUtil.minimumVerticalVector(a,b),expectedResult);
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