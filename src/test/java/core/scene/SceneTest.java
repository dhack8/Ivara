package core.scene;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by james on 16/09/2017.
 */
public class SceneTest {

    Camera camera;
    Scene scene;

    @Before
    public void setUp() throws Exception {
        camera = new Camera();
        scene = new Scene(){};
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void write() throws Exception {
        scene.write();
    }

}