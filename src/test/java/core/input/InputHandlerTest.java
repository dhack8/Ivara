package core.input;

import backends.InputBroadcaster;
import jdk.internal.util.xml.impl.Input;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;

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
        InputHandler.InputFrame inputHandler = new InputHandler.InputFrame();

        for (int i = 0; i < 100; i++) {
            assertFalse(inputHandler.isKeyPressed(i));
            testBroadcaster.broadcastKeyPress(i);
            assertTrue(inputHandler.isKeyPressed(i));
            testBroadcaster.broadcastKeyRelease(i);
            assertFalse(inputHandler.isKeyPressed(i));
        }
    }

    @Test
    public void testMouseInput1() throws Exception {
        TestInputBroadcaster testBroadcaster = new TestInputBroadcaster();
        InputHandler.InputFrame inputHandler = new InputHandler.InputFrame();

        for (int i = 0; i < 100; i++) {
            assertFalse(inputHandler.isMousePressed(i));
            testBroadcaster.broadcastMousePress(i, new Vector(0, 0));
            assertTrue(inputHandler.isMousePressed(i));
            testBroadcaster.broadcastMouseRelease(i, new Vector(0, 0));
            assertFalse(inputHandler.isMousePressed(i));
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