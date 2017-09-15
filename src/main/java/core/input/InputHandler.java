package core.input;

/**
 * Stores the input passed in by Game for use by the other libraries.
 *
 * @author Will Pearson
 * @version 1.0
 */
public class InputHandler {

    private static boolean[] keyPressed = new boolean[525];
    private static boolean[] mousePressed = new boolean[38];

    /**
     * Sets the key to whether it is pressed or not.
     * @param pressed key pressed.
     * @param code key code
     */
    public static void setKeyPressed(boolean pressed, int code) {
        keyPressed[code] = pressed;
    }

    /**
     * Sets the mouse button to being pressed or not.
     * @param pressed mouse pressed
     * @param button button number
     */
    public static void setMousePressed(boolean pressed, int button) {
        mousePressed[button] = pressed;
    }

    /**
     * Checks if the given key is pressed
     * @param code keyCode
     * @return true if the key is pressed.
     */
    public static boolean keyPressed(int code) {
        return keyPressed[code];
    }

    /**
     * Checks if the given mouse button is pressed.
     * @param button mouseButton
     * @return true if the button is pressed
     */
    public static boolean mousePressed(int button) {
        return mousePressed[button];
    }

    /**
     * May not be required
     * @param listener listener
     */
    private static void addMouseListener(MouseListener listener) {
        throw new UnsupportedOperationException();
    }

    /**
     * Clears all input.
     */
    static void clear() {
        mousePressed = new boolean[10];
        keyPressed = new boolean[222];
    }
}
