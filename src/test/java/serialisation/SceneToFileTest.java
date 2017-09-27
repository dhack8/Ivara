package serialisation;

import core.scene.Scene;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ivara.scenes.LevelOne;

/**
 * Created by james on 16/09/2017.
 */
public class SceneToFileTest {

    SceneToFile stf;
    Scene scene;

    @Before
    public void setUp() throws Exception {
        scene = new LevelOne();
        stf = new SceneToFile(scene);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void write() throws Exception {

        stf.write();
    }

}