package core;

import core.input.InputHandler;
import core.renderer.PWindow;
import core.input.KeyListener;
import core.input.MouseListener;
import core.renderer.Renderer;
import core.renderer.PRenderer;
import processing.core.PApplet;

/**
 * Created by Callum Li on 9/14/17.
 */
public abstract class Game extends PApplet {

    // Time Keepers in milliseconds.
    private long past = System.currentTimeMillis();
    private long accumulator = 0;

    // Update Ticks Time in milliseconds.
    private long tickTime = 17;

    private PRenderer renderer;

    @Override
    public void setup() {
        super.setup();
    }

    public void mainLoop() {

    }
    @Override
    public void settings() {
        size(600, 660);
    }

    @Override
    public void draw() {
        accumulator += System.currentTimeMillis() - past;
        if (accumulator >= tickTime) {
            // do Tick
            accumulator -= tickTime;
        }
        // do rendering
        past = System.currentTimeMillis();
    }

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
    /**
     * Handles all input and passes it to the InputHandler.
     */
    private class InputUpdater implements KeyListener, MouseListener {
        //----------------------Input Handler---------------------

        @Override
        public void mousePressed() {
            InputHandler.setMousePressed(true, mouseButton);
        }

        @Override
        public void mouseReleased() {
            InputHandler.setMousePressed(false, mouseButton);
        }

        @Override
        public void keyPressed() {
            InputHandler.setKeyPressed(true, keyCode);
        }

        @Override
        public void keyReleased() {
            InputHandler.setKeyPressed(false, keyCode);
        }
    }

