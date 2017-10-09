package core;


import backends.InputBroadcaster;
import backends.Renderer;
import core.input.InputHandler;
import core.scene.Scene;
import core.scene.LevelManager;

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

    private final InputHandler inputHandler;
    private InputHandler.InputFrame inputFrame = new InputHandler.InputFrame();

    public Game(LevelManager lm, Renderer renderer, final InputBroadcaster inputBroadcaster) {
        this.renderer = renderer;
        this.inputBroadcaster = inputBroadcaster;
        this.inputHandler = new InputHandler(inputBroadcaster);
        this.levelManager = lm;

        //renderer.setMask(1);

        lm.setGame(this);

        assert inputBroadcaster != null;
    }

    public Scene getCurrentScene() {
        return levelManager.getCurrentScene();
    }

    public void setCurrentScene(int level) {
        levelManager.setScene(level);
    }

    public void nextScene(){
        levelManager.nextScene();
    }

    public void pause(){
        levelManager.pause();
    }

    /**
     * Retrieves the current input frame.
     * @return
     */
    public InputHandler.InputFrame getInputFrame() {
        return inputFrame;
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
                inputFrame = inputHandler.nextInputFrame();
                levelManager.getCurrentScene().update(tickTime);

                accumulator -= tickTime;
            }

            // Display the current scene.
            renderer.render(levelManager.getCurrentScene());

        }
    }


}
