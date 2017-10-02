package core;


import backends.InputBroadcaster;
import backends.Renderer;
import core.input.InputHandler;
import core.scene.Scene;
import core.input.KeyListener;
import core.input.MouseListener;
import ivara.scenes.LevelManager;

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
    //private Scene currentScene;
    private LevelManager levelManager;

    /**
     * Backend Dependent Renderer
     */
    private final Renderer renderer;

    private final InputBroadcaster inputBroadcaster;

    public final InputHandler inputHandler;

    public Game(LevelManager lm, Renderer renderer, final InputBroadcaster inputBroadcaster) {
        this.renderer = renderer;
        this.inputBroadcaster = inputBroadcaster;
        this.inputHandler = new InputHandler(inputBroadcaster);
        this.levelManager = lm;

        //todo need to change
        setCurrentScene(0); // levelManager.getCurrentScene().setGame(this);

        assert inputBroadcaster != null;
    }

    public Scene getCurrentScene() {
        return levelManager.getCurrentScene();
    }

    public void setCurrentScene(int level) {
        levelManager.setScene(level, this);
    }

    public void nextScene(){
        levelManager.nextScene(this);
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
                levelManager.getCurrentScene().update(tickTime);

                accumulator -= tickTime;
            }

            // Display the current scene.

            renderer.render(levelManager.getCurrentScene());

        }
    }


}
