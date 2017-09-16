package core.scene;

import core.scene.Camera;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the camera class.
 *
 * @author David Hack
 */
public class CameraTest {

    Camera testCam;

    /**
     * Sets up the testCam.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testCam = new Camera();
    }

    /**
     * Tests that the move method works.
     * @throws Exception
     */
    @Test
    public void move() throws Exception {
        confirmLocation(0, 0);

        testCam.move(1,1);
        confirmLocation(1, 1);

        testCam.move(-1, -1);
        confirmLocation(0, 0);

        testCam.move(0.1f, 0.1f);
        confirmLocation(0.1f, 0.1f);

        testCam.move(-0.1f, -0.1f);
        confirmLocation(0, 0);

        testCam.move(500, -500);
        confirmLocation(500, -500);

        testCam.move(-500, 500);
        confirmLocation(0, 0);
    }

    /**
     * Tests that setDimension works.
     * @throws Exception
     */
    @Test
    public void setDimension() throws Exception {
        confirmDimension(999, 999);

        testCam.setDimension(0, 0);
        confirmDimension(0, 0);

        testCam.setDimension(1920, 1080);
        confirmDimension(1920, 1080);
    }

    /**
     * Tests that setScale works.
     * @throws Exception
     */
    @Test
    public void setScale() throws Exception {
        confirmScale(100.0f);

        testCam.setScale(999);
        confirmScale(999);

        testCam.setScale(0.001f);
        confirmScale(0.001f);
    }

    /**
     * Tests that getLocation works.
     * @throws Exception
     */
    @Test
    public void getLocation() throws Exception {
        Vector location;

        location = testCam.getLocation();
        confirmLocation(location.x, location.y);

        testCam.move(-5,9);

        location = testCam.getLocation();
        confirmLocation(location.x, location.y);
    }

    /**
     * Tests that getDimension works.
     * @throws Exception
     */
    @Test
    public void getDimension() throws Exception {
        Vector dimension;

        dimension = testCam.getDimension();
        confirmDimension(dimension.x, dimension.y);

        testCam.setDimension(-5,9);

        dimension = testCam.getDimension();
        confirmDimension(dimension.x, dimension.y);
    }

    /**
     * Tests that getScale works.
     * @throws Exception
     */
    @Test
    public void getScale() throws Exception {
        double scale = testCam.getScale();

        scale = testCam.getScale();
        confirmScale(scale);

        testCam.setScale(1.5f);

        scale = testCam.getScale();
        confirmScale(scale);
    }

    /**
     * Helps confirm the location is certain values.
     * @param x x test location
     * @param y y test location
     */
    private void confirmLocation(float x, float y){
        Vector location = testCam.getLocation();
        assertEquals(x, location.x, 0.0001);
        assertEquals(y, location.y, 0.0001);
    }

    /**
     * Helps confirm the dimension is certain values.
     * @param x x test location
     * @param y y test location
     */
    private void confirmDimension(float x, float y){
        Vector dimension = testCam.getDimension();
        assertEquals(x, dimension.x, 0.0001);
        assertEquals(y, dimension.y, 0.0001);
    }

    /**
     * Checks the scale is a value.
     * @param s the scale value to test for.
     */
    private void confirmScale(double s){
        assertEquals(s, testCam.getScale(), 0.0001);
    }
}