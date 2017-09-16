package core;


import core.input.InputBroadcaster;
import core.input.InputHandler;
import core.input.KeyListener;
import core.input.MouseListener;
import core.renderer.Renderer;

/**
 * Created by Callum Li on 9/14/17.
 */
public abstract class Game {

    // Time Keepers in milliseconds.
    private long past = System.currentTimeMillis();
    private long accumulator = 0;

    // Update Ticks Time in milliseconds.
    private long tickTime = 17;

    private Renderer renderer;
    private InputBroadcaster inputBroadcaster;
    private InputUpdater inputUpdater;

    public Game() {
        inputUpdater = new InputUpdater();
        inputBroadcaster.addKeyListener(inputUpdater);
        inputBroadcaster.addMouseListener(inputUpdater);
    }


    public void draw() {
        accumulator += System.currentTimeMillis() - past;
        if (accumulator >= tickTime) {
            // do Tick
            accumulator -= tickTime;
        }
        // do rendering
        // insert rendering code here.
        past = System.currentTimeMillis();
    }

    /**
     * Handles all input and passes it to the InputHandler.
     */
    private class InputUpdater implements KeyListener, MouseListener {

        @Override
        public void setKeyPressed(boolean b, int keyCode) {
            InputHandler.setKeyPressed(b, keyCode);
        }

        @Override
        public void setMousePressed(boolean b, int mouseButton) {
            InputHandler.setMousePressed(b, mouseButton);
        }
    }
}
