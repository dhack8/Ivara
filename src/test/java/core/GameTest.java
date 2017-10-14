package core;

import backends.InputBroadcaster;
import backends.Renderer;
import core.input.KeyListener;
import core.input.MouseListener;
import core.scene.LevelManager;
import core.scene.Scene;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by David Hack Local on 06-Oct-17.
 */
public class GameTest {

    @Test
    public void getCurrentScene() throws Exception {
        Scene s = new TestScene();
        Game testGame = new testGame(new LevelManager(s), new testRenderer(), new testBroadcaster());
        assertEquals(s, testGame.getCurrentScene());
    }

    @Test
    public void setCurrentScene() throws Exception {
        Scene s = new TestScene();
        Scene s2 = new TestScene();

        List<Scene> scenes = new ArrayList<>();
        scenes.add(s);
        scenes.add(s2);

        Game testGame = new testGame(new LevelManager(scenes), new testRenderer(), new testBroadcaster());

        testGame.setCurrentScene(0);
        assertEquals(s, testGame.getCurrentScene());

        testGame.setCurrentScene(1);
        assertEquals(s2, testGame.getCurrentScene());
    }

    @Test
    public void nextScene() throws Exception {
        Scene s = new TestScene();
        Scene s2 = new TestScene();

        List<Scene> scenes = new ArrayList<>();
        scenes.add(s);
        scenes.add(s2);

        Game testGame = new testGame(new LevelManager(scenes), new testRenderer(), new testBroadcaster());
        assertEquals(s, testGame.getCurrentScene());

        testGame.nextScene();
        assertEquals(s2, testGame.getCurrentScene());
    }

    @Test
    public void pause() throws Exception {
        Scene s = new TestScene();
        Scene s2 = new TestScene();

        LevelManager lm = new LevelManager(s);
        lm.setPauseMenu(s2);

        Game testGame = new testGame(lm, new testRenderer(), new testBroadcaster());
        assertEquals(s, testGame.getCurrentScene());

        testGame.pause();
        assertEquals(s2, testGame.getCurrentScene());

        testGame.pause();
        assertEquals(s, testGame.getCurrentScene());
    }

    @Test
    public void getInputFrame() throws Exception {
        Scene s = new TestScene();
        Game testGame = new testGame(new LevelManager(s), new testRenderer(), new testBroadcaster());
        testGame.getInputFrame();
    }


    class testGame extends Game{
        public testGame(LevelManager lm, Renderer renderer, InputBroadcaster inputBroadcaster) {
            super(lm, renderer, inputBroadcaster);
        }
    }


    class testRenderer extends Renderer{
        public void render(Scene scene){}
        public void setMask(int i){}
    }

    class testBroadcaster implements InputBroadcaster{
        public void addKeyListener(KeyListener listener){}
        public void addMouseListener(MouseListener listener){}
    }

    private class TestScene extends Scene{
        @Override
        public void startScene() {

        }
    }

}