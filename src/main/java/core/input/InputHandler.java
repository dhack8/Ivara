package core.input;

import backends.InputBroadcaster;
import maths.Vector;

import java.util.BitSet;

/**
 * Stores the input passed in by Game for use by the other libraries.
 *
 * @author Will Pearson
 * @version 1.0
 */
public class InputHandler {

    private InputFrame inputFrame = new InputFrame();
    private InputListener listener = new InputListener();

    public InputHandler() {}

    public InputHandler(InputBroadcaster broadcaster) {
        broadcaster.addMouseListener(listener);
        broadcaster.addKeyListener(listener);
    }


    /**
     * Retrieves the an input frame for the current frame and performs
     * clearing and preparation for the next input frame.
     * @return The current input frame.
     */
    public InputFrame nextInputFrame() {
        InputFrame current = inputFrame;
        inputFrame = new InputFrame(current);
        return current;
    }

    /**
     * May not be required
     * @param listener listener
     */
    private static void addMouseListener(MouseListener listener) {
        throw new UnsupportedOperationException();
    }


    private class InputListener implements KeyListener, MouseListener {

        @Override
        public void mousePressed(int mouseButton, Vector position) {
            inputFrame.pressedMouse.set(mouseButton, true);
            inputFrame.mousePosition = new Vector(position.x, position.y);
        }

        @Override
        public void mouseReleased(int mouseButton, Vector position) {
            inputFrame.pressedMouse.set(mouseButton, false);
            inputFrame.releasedMouse.set(mouseButton, true);
            inputFrame.mousePosition = new Vector(position.x, position.y);
        }

        @Override
        public void mouseMoved(Vector position) {
            inputFrame.mousePosition = new Vector(position.x, position.y);
        }

        @Override
        public void keyPressed(int keyCode) {
            inputFrame.pressedKeys.set(keyCode, true);
        }

        @Override
        public void keyReleased(int keyCode) {
            inputFrame.pressedKeys.set(keyCode, false);
            inputFrame.releasedKeys.set(keyCode, true);
        }
    }

    public static class InputFrame {
        private BitSet pressedKeys = new BitSet();
        private BitSet releasedKeys = new BitSet();
        private BitSet pressedMouse = new BitSet();
        private BitSet releasedMouse = new BitSet();
        private Vector mousePosition = new Vector(0, 0);

        /**
         * Creates a clear input frame.
         */
        public InputFrame() {

        }

        /**
         * Creates an input frame based a preceeding input frame.
         * @param previous
         */
        public InputFrame(InputFrame previous) {
            this.pressedKeys = previous.pressedKeys;
            this.pressedMouse = previous.pressedMouse;
            this.mousePosition = previous.mousePosition;
        }


        /**
         * Returns whether the given key is pressed.
         * @param keyCode The key.
         * @return True if pressed, otherwise false.
         */
        public boolean isKeyPressed(int keyCode) {
            return pressedKeys.get(keyCode);
        }

        /**
         * Returns whether the given key is released in this frame.
         * @param keyCode The key.
         * @return True if pressed, otherwise false.
         */
        public boolean isKeyReleased(int keyCode) {
            return releasedKeys.get(keyCode);
        }

        /**
         * Returns whether the given mouse button is pressed.
         * @param button The button.
         * @return True if pressed, otherwise false.
         */
        public boolean isMousePressed(int button) {
            return pressedMouse.get(button);
        }

        /**
         * Returns whether the given mouse button is released in this frame.
         * @param button The button.
         * @return True if pressed, otherwise false.
         */
        public boolean isMouseReleased(int button) {
            return releasedMouse.get(button);
        }

        /**
         * Returns the mouse's position in world space.
         * @return The mouse's position in world space.
         */
        public Vector getMousePosition() {
            return mousePosition;
        }
    }
}
