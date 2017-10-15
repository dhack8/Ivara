package ivara;

import backends.InputBroadcaster;
import backends.Renderer;
import backends.processing.PWindow;
import com.jogamp.opengl.GLException;
import core.Game;
import core.scene.LevelManager;
import core.scene.Scene;
import ivara.scenes.*;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Callum Li on 9/15/17.
 */
public class Ivara extends Game {

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

        //LevelManager l = new LevelManager(getLevels(), new TestLevel1());
        LevelManager l = new LevelManager(getLevels(), new PauseMenu());
        //LevelManager l = new LevelManager(getLevels());

        Game g = new Ivara(l, processingBackend, processingBackend);
        g.start();
    }

    /**
     * Static method that returns a List of levels (ordered) to construct the LevelManager with
     * @return
     */
    private static List<Scene> getLevels(){
        List<Scene> levels = new ArrayList<>();

        levels.add(new StartMenu());
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        levels.add(new Level5());
        levels.add(new Level6());
        return levels;
    }

}
