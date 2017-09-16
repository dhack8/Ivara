package core;


import core.input.InputBroadcaster;
import core.input.InputHandler;
import core.renderer.PWindow;
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
    **/
}
