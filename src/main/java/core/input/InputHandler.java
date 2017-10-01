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


    private BitSet pressedKeys = new BitSet();
    private BitSet pressedMouse = new BitSet();
    private InputListener listener = new InputListener();
    private Vector mousePosition = null;

    public InputHandler() {}

    public InputHandler(InputBroadcaster broadcaster) {
        broadcaster.addMouseListener(listener);
        broadcaster.addKeyListener(listener);
    }

    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.get(keyCode);
    }

    public boolean isMousePressed(int button) {
        return pressedMouse.get(button);
    }

    public Vector getMousePosition() {
        return mousePosition;
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
            pressedMouse.set(mouseButton, true);
            mousePosition = position;
        }

        @Override
        public void mouseReleased(int mouseButton, Vector position) {
            pressedMouse.set(mouseButton, false);
            mousePosition = position;
        }

        @Override
        public void keyPressed(int keyCode) {
            pressedKeys.set(keyCode, true);
        }

        @Override
        public void keyReleased(int keyCode) {
            pressedKeys.set(keyCode, false);
        }
    }
}
