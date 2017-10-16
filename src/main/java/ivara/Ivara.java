package ivara;

import backends.InputBroadcaster;
import backends.Renderer;
import backends.processing.PWindow;
import backends.tinysound.Music;
import backends.tinysound.TinySound;
import com.jogamp.opengl.GLException;
import core.Game;
import core.scene.LevelManager;
import core.scene.Scene;
import ivara.scenes.*;
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

        LevelManager l = new LevelManager(getLevels(), new PauseMenu());

        Game g = new Ivara(l, processingBackend, processingBackend);
        backgroundTrack.play(true);
        g.start();
    }

    /**
     * Static method that returns a List of levels (ordered) to construct the LevelManager with.
     * @return The levels to play (in order).
     */
    private static List<Scene> getLevels(){
        List<Scene> levels = new ArrayList<>();

        levels.add(new StartMenu());
        levels.add(new Level2());
        levels.add(new Level1());
        levels.add(new Level3());
        levels.add(new Level4());
        levels.add(new Level5());
        levels.add(new Level6());
        levels.add(new HardLevel1());
        levels.add(new HardLevel2());
        levels.add(new HardLevel3());
        return levels;
    }

    /**
     * Gets the background sound file.
     * @return The backing track.
     */
    public static Music getBackgroundTrack(){
        return backgroundTrack;
    }

}
