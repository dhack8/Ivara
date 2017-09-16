package core.renderer;

import core.scene.Entity;
import core.scene.Scene;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test file for the PWindow.
 *
 * @author David Hack
 */
public class PWindowTest {

    PWindow window;
    Scene scene;

    @Before
    public void setUp() throws Exception {
        window = new PWindow(1800, 900);
        scene = new Scene() {};
    }

    @Test
    public void render() throws Exception {
        Entity test
    }

}