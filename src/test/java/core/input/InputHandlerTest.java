package core.input;

import backends.InputBroadcaster;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * InputHandler unit tests.
 *
 * @author Will Pearson
 */
public class InputHandlerTest {


    /**
     * Tests pressing each key individually
     * @throws Exception
     */
    @Test
    public void testKeyInput1() throws Exception {
        TestInputBroadcaster testBroadcaster = new TestInputBroadcaster();
        InputHandler inputHandler = new InputHandler(testBroadcaster);

        Field f = inputHandler.getClass().getDeclaredField("inputFrame"); //NoSuchFieldException
        f.setAccessible(true);
        InputHandler.InputFrame inputFrame = (InputHandler.InputFrame) f.get(inputHandler); //IllegalAccessException

        for (int i = 0; i < 100; i++) {
            assertFalse(inputFrame.isKeyPressed(i));
            testBroadcaster.broadcastKeyPress(i);
            assertTrue(inputFrame.isKeyPressed(i));
            testBroadcaster.broadcastKeyRelease(i);
            assertFalse(inputFrame.isKeyPressed(i));
        }
    }

    @Test
    public void testMouseInput1() throws Exception {
        TestInputBroadcaster testBroadcaster = new TestInputBroadcaster();
        InputHandler inputHandler = new InputHandler(testBroadcaster);

        Field f = inputHandler.getClass().getDeclaredField("inputFrame"); //NoSuchFieldException
        f.setAccessible(true);
        InputHandler.InputFrame inputFrame = (InputHandler.InputFrame) f.get(inputHandler); //IllegalAccessException


        for (int i = 0; i < 100; i++) {
            assertFalse(inputFrame.isMousePressed(i));
            testBroadcaster.broadcastMousePress(i, new Vector(0, 0));
            assertTrue(inputFrame.isMousePressed(i));
            testBroadcaster.broadcastMouseRelease(i, new Vector(0, 0));
            assertFalse(inputFrame.isMousePressed(i));
        }
    }


    public static class TestInputBroadcaster implements InputBroadcaster {

        private KeyListener keyListener;
        private MouseListener mouseListener;

        public void broadcastKeyPress(int keyCode) {
            keyListener.keyPressed(keyCode);
        }

        public void broadcastKeyRelease(int keyCode) {
            keyListener.keyReleased(keyCode);
        }

        public void broadcastMousePress(int button, Vector position) {
            mouseListener.mousePressed(button, position);
        }

        public void broadcastMouseRelease(int button, Vector position) {
            mouseListener.mouseReleased(button, position);
        }

        @Override
        public void addKeyListener(KeyListener listener) {
            keyListener = listener;
        }

        @Override
        public void addMouseListener(MouseListener listener) {
            mouseListener = listener;
        }
    }
}