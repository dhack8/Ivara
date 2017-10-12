package maths;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the Vector class.
 * @author David Hack
 */
public class VectorTest {
    /**
     * Tests that set works
     * @throws Exception
     */
    @Test
    public void set() throws Exception {

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
     * Tests that add works correctly
     * @throws Exception
     */
    @Test
    public void add() throws Exception {

        float test1 = 4.45435f;
        float test2 = 2.46436f;

        Vector testVector = new Vector(0,0);
        testVector.incrementBy(test1, test2);
        assertEquals(testVector.x, test1, 0.000001f);
        assertEquals(testVector.y, test2, 0.000001f);
    }

    /**
     * Test that add(Vector) works correctly
     * @throws Exception
     */
    @Test
    public void add1() throws Exception {
        float test1 = 4.45435f;
        float test2 = 2.46436f;

        Vector testVector = new Vector(0,0);
        testVector.incrementBy(new Vector(test1, test2));
        assertEquals(testVector.x, test1, 0.000001f);
        assertEquals(testVector.y, test2, 0.000001f);
    }

    /**
     * Tests that scale works correctly
     * @throws Exception
     */
    @Test
    public void scale() throws Exception {

        float test1 = 445.2352f;
        float test2 = -6.4754f;

        Vector testVector = new Vector(test1,test2);
        testVector.scaleBy(2.0f);
        assertEquals(testVector.x, test1 * 2.0f, 0.000001f);
        assertEquals(testVector.y, test2 * 2.0f, 0.000001f);
    }
}