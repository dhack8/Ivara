package physics;

import maths.Vector;
import org.junit.Test;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Callum Li on 10/1/17.
 */
public class CollisionUtilTest {

    /**
     * Tests that an intersection is detected between two
     * intersecting AABBColliders.
     * @throws Exception
     */
    @Test
    public void intersectionTest1() throws Exception {
        Collider aabb1 = generateAABBCollider(0, 0, 1, 1);
        Collider aabb2 = generateAABBCollider(0.5f, 0.5f, 1, 1);

        assertTrue(CollisionUtil.intersect(aabb1, aabb2));
    }

    /**
     * Tests that an intersection is not detected between two
     * non intersecting AABBColliders.
     * @throws Exception
     */
    @Test
    public void intersectionTest2() throws Exception {
        Collider aabb1 = generateAABBCollider(0, 0, 1, 1);
        Collider aabb2 = generateAABBCollider(1.5f, 0, 1, 1);

        assertFalse(CollisionUtil.intersect(aabb1, aabb2));
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
     * Helper method for generating AABBColliders quickly. The given
     * cooridinates are the values for the top left corner and width
     * and height. (In that order).
     * @param cx The minimum x
     * @param cy The minimum y
     * @param rx The width
     * @param ry The height
     * @return An AABBCollider
     */
    private AABBCollider generateAABBCollider(float cx, float cy, float rx, float ry) {
        return new AABBCollider(AABBCollider.MIN_DIM, new Vector(cx, cy), new Vector(rx, ry));
    }
}