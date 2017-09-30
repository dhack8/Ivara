package core.input;

/**
 * Stores the input passed in by Game for use by the other libraries.
 *
 * @author Will Pearson
 * @version 1.0
 */
public class InputHandler {

    // Constants
    private static int MAX_KEY_CODES = 525;
    private static int MAX_MOUSE_CODES = 38;
    public static final int W = 87;
    public static final int A = 65;
    public static final int S = 83;
    public static final int D = 68;
    public static final int SPACE = 32;

    public static final int LEFT_MOUSE = 37;

    private static boolean[] keyPressed = new boolean[MAX_KEY_CODES];
    private static boolean[] mousePressed = new boolean[MAX_MOUSE_CODES];

    /**
     * Sets the key to whether it is pressed or not.
     * @param pressed key pressed.
     * @param code key code
     */
    public static void setKeyPressed(boolean pressed, int code) {
        try {
            keyPressed[code] = pressed;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Key code out of range: " + code);
        }
    }

    /**
     * Sets the mouse button to being pressed or not.
     * @param pressed mouse pressed
     * @param button button number
     */
    public static void setMousePressed(boolean pressed, int button) {
        try {
            mousePressed[button] = pressed;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Mouse button out of range: " + button);
        }
    }

    /**
     * Checks if the given key is pressed
     * @param code keyCode
     * @return true if the key is pressed.
     */
    public static boolean keyPressed(int code) {
        try {
            return keyPressed[code];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Key code out of range: " + code);
            return false;
        }
    }

    /**
     * Checks if the given mouse button is pressed.
     * @param button mouseButton
     * @return true if the button is pressed
     */
    public static boolean mousePressed(int button) {
        try {
            return mousePressed[button];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Mouse button out of range: " + button);
            return false;
        }
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
        mousePressed = new boolean[MAX_MOUSE_CODES];
        keyPressed = new boolean[MAX_KEY_CODES];
    }
}
