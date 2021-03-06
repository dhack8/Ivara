package core;


import backends.InputBroadcaster;
import backends.Renderer;
import core.input.InputHandler;
import core.scene.Scene;
import core.scene.LevelManager;
import kuusisto.tinysound.TinySound;
import processing.core.PApplet;

import java.io.*;

/**
 * Main game where everything comes together.
 * @author Callum Li
 */
public abstract class Game {

    private static final String SAVE_FILE = "./savefile.ser";

    static {
        TinySound.init();
    }

    /**
     * The number of milliseconds per update tick.
     */
    private int tickTime = 10;

    /**
     * The current scene of the game.
     */
    private LevelManager levelManager;

    /**
     * Backend Dependent Renderer
     */
    private final Renderer renderer;

    private final InputBroadcaster broadcaster;

    private final InputHandler inputHandler;

    private InputHandler.InputFrame inputFrame = new InputHandler.InputFrame();

    /**
     * Constructs a game with all the required aspects
     * @param lm level manager
     * @param renderer backend renderer
     * @param broadcaster input broadcaster
     */
    public Game(LevelManager lm, Renderer renderer, final InputBroadcaster broadcaster) {
        this.renderer = renderer;
        this.broadcaster = broadcaster;
        this.inputHandler = new InputHandler(broadcaster);
        this.levelManager = lm;

        renderer.setMask(1);

        TinySound.init();

        lm.setGame(this);
        assert broadcaster != null;
    }

    /**
     * Returns the the window for closing within the implantation.
     * @return the renderer
     */
    public PApplet getWindow() {
        return renderer;
    }

    public LevelManager getLevelManager() {
        return levelManager;
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

    public void save(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE)); ) {
            out.writeObject(levelManager);
            System.out.println("Saved model to: " + SAVE_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE)); ) {
            LevelManager levelManager = (LevelManager) in.readObject();
            levelManager.setGame(this);
            System.out.println("Loaded game");
            this.levelManager = levelManager;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the game.
     */
    final public void start() {

        // Time Keepers in milliseconds.
        long past = System.currentTimeMillis();
        long accumulator = 0;

        while (true) {
            long delta = System.currentTimeMillis() - past;

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

            // Display the current scene. BLOCKING
            renderer.render(levelManager.getCurrentScene());
        }
    }
}
