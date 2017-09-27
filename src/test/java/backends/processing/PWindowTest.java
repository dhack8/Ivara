package backends.processing;

import core.scene.Scene;
import org.junit.Test;
import processing.core.PApplet;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by David Hack Local on 27-Sep-17.
 */
public class PWindowTest {
    @Test
    public void render() throws Exception {

        PWindow testWindow = new PWindow();
        PApplet.runSketch(new String[]{"Sketch Demo"}, testWindow);




        while(true) {
            //testWindow.render(testScene);
        }

    }

    public class TestScene extends Scene{
        public TestScene(){

        }
    }
}