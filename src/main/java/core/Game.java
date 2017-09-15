package core;


import core.input.InputBroadcaster;
import core.input.InputHandler;
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
