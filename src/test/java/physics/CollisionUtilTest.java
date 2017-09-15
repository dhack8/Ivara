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
        AABBCollider a = new AABBCollider(new Vector(0,0), new Vector(10, 10));
        AABBCollider b = new AABBCollider(new Vector(0,0), new Vector(10, 10));

        assertTrue(CollisionUtil.intersect(a,b));
    }

    /**
     * Tests corner intersection.
     * @throws Exception
     */
    @Test
    public void intersectTest2() throws Exception {
        AABBCollider a = new AABBCollider(new Vector(-5,-5), new Vector(6, 6));
        AABBCollider b = new AABBCollider(new Vector(5,5), new Vector(5, 5));

        assertTrue(CollisionUtil.intersect(a,b));
    }

    /**
     * Tests for no corner intersection.
     * @throws Exception
     */
    @Test
    public void intersectTest3() throws Exception {
        AABBCollider a = new AABBCollider(new Vector(-5,-5), new Vector(4, 4));
        AABBCollider b = new AABBCollider(new Vector(5,5), new Vector(5, 5));

        assertFalse(CollisionUtil.intersect(a,b));
    }

}