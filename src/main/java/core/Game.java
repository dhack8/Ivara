package core;


import core.input.InputBroadcaster;
import core.input.InputHandler;
import core.scene.Scene;
import core.input.KeyListener;
import core.input.MouseListener;
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

    private Scene scene;
    private InputBroadcaster inputBroadcaster;
    private InputUpdater inputUpdater = new InputUpdater();

    public Game() {
        inputUpdater = new InputUpdater();
    }

    public void setInputBroadcaster(InputBroadcaster inputBroadcaster) {
        this.inputBroadcaster = inputBroadcaster;
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

    public void start() {
        //setInputBroadcaster(window);

        while (true) {
            mainLoop();
        }
    }

    public Scene getScene() {
        System.out.println("Getting scene");
        return scene;
    }

    public void setScene(Scene scene) {
        System.out.println("Setting scene");
        System.out.println("Scene null: " + (scene ==null));
        this.scene = scene;
    }

    //----------------------Input Handler---------------------

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
