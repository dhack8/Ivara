package core.input;

import maths.Vector;

/**
 * Listens for mouse input.
 */
public interface MouseListener {

    /**
     * Mouse button is pressed.
     * @param mouseButton mouse button
     * @param position position on screen
     */
    void mousePressed(int mouseButton, Vector position);

    /**
     * Mouse button is released
     * @param mouseButton mouse button
     * @param position position on screen
     */
    void mouseReleased(int mouseButton, Vector position);

    /**
     * Mouse is moved
     * @param position position on screen
     */
    void mouseMoved(Vector position);
}
