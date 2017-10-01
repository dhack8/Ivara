package core;


import backends.InputBroadcaster;
import backends.Renderer;
import core.input.InputHandler;
import core.scene.Scene;
import core.input.KeyListener;
import core.input.MouseListener;

/**
 * Created by Callum Li on 9/14/17.
 */
public abstract class Game {

    /**
     * The number of milliseconds per update tick.
     */
    private int tickTime = 10;

    /**
     * The current scene of the game.
     */
    private Scene currentScene;

    /**
     * Backend Dependent Renderer
     */
    private final Renderer renderer;

    private final InputBroadcaster inputBroadcaster;

    public Game(Scene initialScene, Renderer renderer, final InputBroadcaster inputBroadcaster) {
        this.renderer = renderer;
        this.inputBroadcaster = inputBroadcaster;
        setCurrentScene(initialScene);

        assert inputBroadcaster != null;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene scene) {
        this.currentScene = scene;
        this.currentScene.setInputHandler(new InputHandler(inputBroadcaster));
    }

    /**
     * Starts the game.
     */
    final public void start() {

        // Time Keepers in milliseconds.
        long past = System.currentTimeMillis();
        long accumulator = 0;

        while (true) {
            accumulator += System.currentTimeMillis() - past;
            past = System.currentTimeMillis();

            // Update the game while more than a tick's worth
            // of time needs to be processed.
            while (accumulator >= tickTime) {
                // do Tick
                currentScene.update(tickTime);

                accumulator -= tickTime;
            }

            // Display the current scene.

            renderer.render(currentScene);

        }
    }


}
