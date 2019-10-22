package core.input;

import backends.InputBroadcaster;
import maths.Vector;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Stores the input passed in by Game for use by the other libraries.
 *
 * @author Will Pearson
 * @version 1.0
 */
public class InputHandler {

    private InputFrame inputFrame = new InputFrame();
    private InputListener listener = new InputListener();

    public InputHandler(){}

    /**
     * Constructs the input handler with a specific input broadcaster.
     * @param broadcaster broadcaster
     */
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


    /**
     * Listener class for user input.
     */
    private class InputListener implements KeyListener, MouseListener {

        /**
         * Mouse pressed event updates input frame
         * @param mouseButton mouse button
         * @param position position on screen
         */
        @Override
        public void mousePressed(int mouseButton, Vector position) {
            inputFrame.pressedMouse.set(mouseButton, true);
            inputFrame.mousePosition = new Vector(position.x, position.y);
        }

        /**
         * Mouse released event updates input frame
         * @param mouseButton mouse button
         * @param position position on screen
         */
        @Override
        public void mouseReleased(int mouseButton, Vector position) {
            inputFrame.pressedMouse.set(mouseButton, false);
            inputFrame.releasedMouse.set(mouseButton, true);
            inputFrame.mousePosition = new Vector(position.x, position.y);
        }

        /**
         * Mouse moved event updates input frame
         * @param position position on screen
         */
        @Override
        public void mouseMoved(Vector position) {
            inputFrame.mousePosition = new Vector(position.x, position.y);
        }

        /**
         * Key pressed event
         * @param keyCode specific key code
         */
        @Override
        public void keyPressed(int keyCode) {
            inputFrame.pressedKeys.set(keyCode, true);
        }

        /**
         * Key released event
         * @param keyCode specific key code
         */
        @Override
        public void keyReleased(int keyCode) {
            inputFrame.pressedKeys.set(keyCode, false);
            inputFrame.releasedKeys.set(keyCode, true);
        }
    }

    /**
     * Input frame holds details on input state.
     */
    public static class InputFrame {
        private BitSet pressedKeys = new BitSet();
        private BitSet releasedKeys = new BitSet();
        private BitSet pressedMouse = new BitSet();
        private BitSet releasedMouse = new BitSet();
        private Vector mousePosition = new Vector(0, 0);

        /**
         * Creates a clear input frame.
         */
        public InputFrame() {}

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

        public Set<Character> getPressedKeys() {
            Set<Character> pressedChars = new HashSet<>();
            for (int i = 0; i < pressedKeys.length(); i++) {
                if(pressedKeys.get(i)) pressedChars.add((char)i);
            }
            return pressedChars;
        }

        public Set<Character> getReleasedKeys() {
            Set<Character> releasedChars = new HashSet<>();
            for (int i = 0; i < releasedKeys.length(); i++) {
                if(releasedKeys.get(i)) releasedChars.add((char)i);
            }
            return releasedChars;
        }
    }
}
