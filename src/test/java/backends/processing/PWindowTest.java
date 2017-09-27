package backends.processing;

import core.components.BasicCameraComponent;
import core.components.PSpriteComponent;
import core.components.ScriptComponent;
import core.entity.Entity;
import core.input.InputHandler;
import core.scene.Scene;
import ivara.entities.NPlatformEntity;
import ivara.entities.PlayerEntity;
import maths.Vector;
import org.junit.Test;
import processing.core.PApplet;
import sun.font.Script;

import javax.swing.*;
import java.util.NoSuchElementException;

import static core.input.InputHandler.SPACE;
import static org.junit.Assert.*;

/**
 * Created by David Hack Local on 27-Sep-17.
 */
public class PWindowTest {

    int delay = 1000;

    @Test
    public void render() throws Exception {

        PWindow testWindow = new PWindow();
        PApplet.runSketch(new String[]{"Sketch Demo"}, testWindow);

        TestScene testScene = new TestScene();

        testWindow.setMask(1);

        long start = System.currentTimeMillis();
        while(true) {
            testWindow.render(testScene);
            if(System.currentTimeMillis() - start > delay){
                break;
            }
        }

        int n = JOptionPane.showConfirmDialog(
                new JFrame(),
                "Did you see a little man center left, standing at the start of a nice platform with nice corners?",
                "Render result",
                JOptionPane.YES_NO_OPTION);

        if(n == 1){
            fail("Human tester detected a error with rendering");
        }

        testScene.moveSprite();

        start = System.currentTimeMillis();
        while(true) {
            testWindow.render(testScene);
            if(System.currentTimeMillis() - start > delay){
                break;
            }
        }

        int m = JOptionPane.showConfirmDialog(
                new JFrame(),
                "Did you see a little man center left, standing at the end of the platform?",
                "Render result",
                JOptionPane.YES_NO_OPTION);

        if(m == 1){
            fail("Human tester detected a error with rendering");
        }
    }

    public class TestScene extends Scene{
        TestEntity tE;

        public TestScene(){
            tE = new TestEntity(2, 1.5f);

            addEntity(tE);
            addEntity(new NPlatformEntity(2,3, 10, false));
        }

        public void moveSprite(){
            tE.moveSprite();
        }
    }

    public class TestEntity extends Entity {
        public TestEntity(float x, float y) {
            super(new Vector(x, y));

            addComponent(new PSpriteComponent(this, "player", 1, 1.5f));
            addComponent(new BasicCameraComponent(this, 19));
        }

        public void moveSprite(){
            translate(9,0);
        }
    }
}