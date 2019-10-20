package ivara;

import backends.InputBroadcaster;
import backends.Renderer;
import backends.processing.PWindow;
import com.jogamp.opengl.GLException;
import core.Game;
import core.scene.LevelManager;
import core.scene.Scene;
import ivara.scenes.*;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * This class extends the functionality of the game engine and runs our game, Ivara.
 * The class brings together the necessary components to run the game.
 * @author Callum Li
 */
public class Ivara extends Game {

    private static final Music backgroundTrack = TinySound.loadMusic("backgroundtrack.wav");

    public static final String MAP = "map";
    public static final String MAIN_MENU = "main_menu";

    /**
     * Constructs the game.
     * @param lm The LevelManager that handles scenes within the game.
     * @param renderer The renderer to display the game.
     * @param broadcaster The input handling component of the game.
     */
    public Ivara(LevelManager lm, Renderer renderer, InputBroadcaster broadcaster) {
        super(lm, renderer, broadcaster);
    }

    public static void main(String[] args) {
        PWindow processingBackend;

        try {
            processingBackend = new PWindow(true);
            PApplet.runSketch(new String[]{"PWindow"}, processingBackend);
        }catch (GLException e){
            processingBackend = new PWindow(false);
            PApplet.runSketch(new String[]{"PWindow"}, processingBackend);
        }

        StartMenu startMenu = new StartMenu();
        LevelManager l = new LevelManager(startMenu, new PauseMenu());
        l.bookmarkScene(MAP, new LevelMap());
        l.bookmarkScene(MAIN_MENU, startMenu);

        Game g = new Ivara(l, processingBackend, processingBackend);
        backgroundTrack.play(true);
        g.start();
    }

    /**
     * Gets the background sound file.
     * @return The backing track.
     */
    public static Music getBackgroundTrack(){
        return backgroundTrack;
    }
}
