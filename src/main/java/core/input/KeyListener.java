package core.input;

/**
 * Listens for key input.
 */
public interface KeyListener {

    /**
     * Key pressed event.
     * @param keyCode Specific key code, refer to constants class
     */
    void keyPressed(int keyCode);

    /**
     * Key released event
     * @param keyCode Specific key code, refer to constants class
     */
    void keyReleased(int keyCode);
}
