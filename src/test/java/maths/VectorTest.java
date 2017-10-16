package maths;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the vector class
 * @author David Hack
 */
public class VectorTest {

    /**
     * Tests that set works.
     * @throws Exception
     */
    @Test
    public void setAsTest() throws Exception {
        float test1 = 4.45435f;
        float test2 = 2.46436f;

        Vector testVector = new Vector(0,0);
        testVector.setAs(test1, test2);
        assertEquals(testVector.x, test1, 0.000001f);
        assertEquals(testVector.y, test2, 0.000001f);

        test1 = 445.2352f;
        test2 = -6.4754f;

        testVector = new Vector(0,0);
        testVector.setAs(test1, test2);
        assertEquals(testVector.x, test1, 0.000001f);
        assertEquals(testVector.y, test2, 0.000001f);

        test1 = -4.45435f;
        test2 = -6.4754f;

        testVector = new Vector(0,0);
        testVector.setAs(test1, test2);
        assertEquals(testVector.x, test1, 0.000001f);
        assertEquals(testVector.y, test2, 0.000001f);
    }

    /**
     * Tests that set(Vector) works.
     * @throws Exception
     */
    @Test
    public void setAs1Test() throws Exception {
        float test1 = 4.45435f;
        float test2 = 2.46436f;

        Vector testVector = new Vector(0,0);
        testVector.setAs(new Vector(test1, test2));
        assertEquals(testVector.x, test1, 0.000001f);
        assertEquals(testVector.y, test2, 0.000001f);

        test1 = 445.2352f;
        test2 = -6.4754f;

        testVector = new Vector(0,0);
        testVector.setAs(new Vector(test1, test2));
        assertEquals(testVector.x, test1, 0.000001f);
        assertEquals(testVector.y, test2, 0.000001f);

        test1 = -4.45435f;
        test2 = -6.4754f;

        testVector = new Vector(new Vector(test1, test2));
        testVector.setAs(test1, test2);
        assertEquals(testVector.x, test1, 0.000001f);
        assertEquals(testVector.y, test2, 0.000001f);
    }

    /**
     * Tests that increment works correctly.
     * @throws Exception
     */
    @Test
    public void incrementByTest() throws Exception {
        float test1 = 4.45435f;
        float test2 = 2.46436f;

        Vector testVector = new Vector(0,0);
        testVector.incrementBy(test1, test2);
        assertEquals(testVector.x, test1, 0.000001f);
        assertEquals(testVector.y, test2, 0.000001f);
    }

    /**
     * Test that increment(Vector) works correctly.
     * @throws Exception
     */
    @Test
    public void incrementBy1Test() throws Exception {
        float test1 = 4.45435f;
        float test2 = 2.46436f;

        Vector testVector = new Vector(0,0);
        testVector.incrementBy(new Vector(test1, test2));
        assertEquals(testVector.x, test1, 0.000001f);
        assertEquals(testVector.y, test2, 0.000001f);
    }

    /**
     * Tests that scale works correctly.
     * @throws Exception
     */
    @Test
    public void scaleByTest() throws Exception {
        float test1 = 445.2352f;
        float test2 = -6.4754f;

        Vector testVector = new Vector(test1,test2);
        testVector.scaleBy(2.0f);
        assertEquals(testVector.x, test1 * 2.0f, 0.000001f);
        assertEquals(testVector.y, test2 * 2.0f, 0.000001f);
    }

    /**
     * Tests that the distance methods works.
     * @throws Exception
     */
    @Test
    public void distTest() throws Exception {
        Vector testVector = new Vector(3,4);
        assertEquals(testVector.dist(new Vector(0,0)), 5, 0.0001f);

        testVector = new Vector(6,0);
        Vector testVector2 = new Vector(3,4);
        assertEquals(testVector.dist(testVector2), 5, 0.0001f);
    }

    /**
     * Tests that magnitude method works
     * @throws Exception
     */
    @Test
    public void magnitudeTest() throws Exception {
        Vector testVector = new Vector(3,4);
        assertEquals(testVector.magnitude(), 5, 0.0001f);

        testVector = new Vector(1,1);
        assertEquals(testVector.magnitude(), Math.sqrt(2), 0.0001f);
    }

    /**
     * Tests that the normalize method works
     * @throws Exception
     */
    @Test
    public void normTest() throws Exception {
        Vector testVector = new Vector(3,4);
        assertEquals(testVector.norm().magnitude(), 1, 0.0001f);

        testVector = new Vector(1,1);
        assertEquals(testVector.norm().magnitude(), 1, 0.0001f);

        testVector = new Vector(1345634,657565);
        assertEquals(testVector.norm().magnitude(), 1, 0.0001f);
    }

    /**
     * Tests that the add method works
     * @throws Exception
     */
    @Test
    public void addTest() throws Exception {
        Vector testVector = new Vector(5,8);
        Vector testVector2 = new Vector(5,8);
        assertEquals(new Vector(10,16), testVector.add(testVector2));

        testVector = new Vector(-4.2f,6.7f);
        testVector2 = new Vector(-2.3f,9.1f);
        Vector result = testVector.add(testVector2);
        assertEquals(-6.5f, result.x, 0.0001f);
        assertEquals(15.8f, result.y, 0.0001f);
    }

    /**
     * Tests that the sub method works
     * @throws Exception
     */
    @Test
    public void subTest() throws Exception {
        Vector testVector = new Vector(5,8);
        Vector testVector2 = new Vector(5,8);
        assertEquals(new Vector(0,0), testVector.sub(testVector2));

        testVector = new Vector(-4.2f,6.7f);
        testVector2 = new Vector(-2.3f,9.1f);
        Vector result = testVector.sub(testVector2);
        assertEquals(-1.9f, result.x, 0.0001f);
        assertEquals(-2.4f, result.y, 0.0001f);
    }

    /**
     * Tests that the equals method works
     * @throws Exception
     */
    @Test
    public void equalsTest() throws Exception {
        Vector testVector = new Vector(5,8);
        Vector testVector2 = new Vector(5,8);
        assertEquals(testVector, testVector2);

        testVector = new Vector(-5.6768f,8.74323f);
        testVector2 = new Vector(-5.6768f,8.74323f);
        assertEquals(testVector, testVector2);
    }

}