package core;


import core.input.InputBroadcaster;
import core.input.InputHandler;
import core.renderer.PWindow;
import core.input.KeyListener;
import core.input.MouseListener;
import core.renderer.Renderer;
import processing.core.PApplet;

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

    public void mainLoop() {
        accumulator += System.currentTimeMillis() - past;
        if (accumulator >= tickTime) {
            // do Tick
            accumulator -= tickTime;
        }
        // do rendering

        // insert rendering code here.
        past = System.currentTimeMillis();
    }

<<<<<<< HEAD
    public void start() {
        PWindow window = new PWindow(600, 600);
        renderer = window;
        PApplet.runSketch( new String[] { "--display=1",
                        "--location=0,0",
                        "--sketch-path=" + window.sketchPath(""),
                        "" },
                window);

        while (true) {
            mainLoop();
        }
    }

    //----------------------Input Handler---------------------

=======
>>>>>>> 27a6ec96a92efbc639a946253cca85717a0243c3
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
