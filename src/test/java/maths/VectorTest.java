package maths;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the vector class
 * @author David Hack
 */
public class VectorTest {

    @Before
    public void setUpTest() throws Exception {

    }

    /**
     * Tests that set works
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
     * Tests that set(Vector) works
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
     * Tests that increment works correctly
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
     * Test that increment(Vector) works correctly
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
     * Tests that scale works correctly
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

    @Test
    public void distTest() throws Exception {

    }

    @Test
    public void magnitudeTest() throws Exception {

    }

    @Test
    public void normTest() throws Exception {

    }

    @Test
    public void addTest() throws Exception {

    }

    @Test
    public void subTest() throws Exception {

    }

    @Test
    public void equalsTest() throws Exception {

    }

    @Test
    public void toStringTest() throws Exception {

    }

}