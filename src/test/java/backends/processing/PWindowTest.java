package backends.processing;

import core.scene.Scene;
import ivara.entities.NPlatformEntity;
import ivara.entities.PlayerEntity;
import org.junit.Test;
import processing.core.PApplet;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Created by David Hack Local on 27-Sep-17.
 */
public class PWindowTest {

    int delay = 800;

    @Test
    public void render() throws Exception {

        PWindow testWindow = new PWindow();
        PApplet.runSketch(new String[]{"PWindow"}, testWindow);

        TestScene testScene = null;

        testWindow.setMask(1);

        long start = System.currentTimeMillis();
        while(true) {
            testWindow.render(testScene);
            if(System.currentTimeMillis() - start > delay){
                break;
            }
        }

        int o = JOptionPane.showConfirmDialog(
                new JFrame(),
                "Did you see red error screen?",
                "Render result",
                JOptionPane.YES_NO_OPTION);

        if(o == 1){
            fail("Human tester detected a error with rendering");
        }

        testScene = new TestScene();

        start = System.currentTimeMillis();
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

        testWindow.setMask(2);

        start = System.currentTimeMillis();
        while(true) {
            testWindow.render(testScene);
            if(System.currentTimeMillis() - start > delay){
                break;
            }
        }

        int z = JOptionPane.showConfirmDialog(
                new JFrame(),
                "Same thing with testing boxes?",
                "Render result",
                JOptionPane.YES_NO_OPTION);

        if(z == 1){
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

    public class TestEntity extends PlayerEntity {
        public TestEntity(float x, float y) {
            super(x,y);
        }

        public void moveSprite(){
            getTransform().add(9,0);
        }
    }
}