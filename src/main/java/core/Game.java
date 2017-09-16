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
    private long tickTime = 17;

    /**
     * The current scene of the game.
     */
    private Scene currentScene;

    /**
     * Backend Dependent Renderer
     */
    private final Renderer renderer;

    public Game(Scene initialScene, Renderer renderer, final InputBroadcaster inputBroadcaster) {
        this.currentScene = initialScene;
        this.renderer = renderer;

        // Update InputHandler based on input event's broadcasted by
        // the input broadcaster.
        inputBroadcaster.addKeyListener(new InputUpdater());
        inputBroadcaster.addMouseListener(new InputUpdater());
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    /**
     * Starts the game.
     */
    final public void start() {

        // Time Keepers in milliseconds.
        long past = System.currentTimeMillis();
        long accumulator = 0;

        while (true) {

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

            accumulator += System.currentTimeMillis() - past;
        }
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
