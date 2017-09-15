package core;

import core.input.InputHandler;
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
