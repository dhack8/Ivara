package core.input;

import maths.Vector;

/**
 * Listens for mouse input
 */
public interface MouseListener {

    void mousePressed(int mouseButton, Vector position);
    void mouseReleased(int mouseButton, Vector position);
    void mouseMoved(Vector position);
}
