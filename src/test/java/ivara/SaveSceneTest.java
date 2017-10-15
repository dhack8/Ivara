package ivara;

import backends.InputBroadcaster;
import backends.Renderer;
import core.Game;
import core.scene.LevelManager;
import ivara.scenes.DefaultScene;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by james on 14/10/2017.
 */
public class SaveSceneTest {

    //BIG TODO
    Game game;
    DefaultScene scene;
    File file;
    LevelManager lm;
    Renderer r;
    InputBroadcaster ib;

    @Before
    public void setUp() throws Exception {
        //lm = new LevelManager()
        Game game = new Ivara(lm, r, ib);
    }

    @Test
    public void save() throws Exception {

    }

}