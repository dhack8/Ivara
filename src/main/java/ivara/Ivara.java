package ivara;

import backends.InputBroadcaster;
import backends.Renderer;
import backends.processing.PWindow;
import com.jogamp.opengl.GLException;
import core.Game;
import core.scene.Scene;
import ivara.scenes.*;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Callum Li on 9/15/17.
 */
public class Ivara extends Game {

    public Ivara(LevelManager lm, Renderer renderer, InputBroadcaster inputBroadcaster) {
        //super(new LevelOne(), renderer, inputBroadcaster);
        //super(new LevelThree(), renderer, inputBroadcaster);
        super(lm, renderer, inputBroadcaster);
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

        //todo

        Game g = new Ivara(new LevelManager(getLevels()), processingBackend, processingBackend);
        g.start();
    }

    private static List<Scene> getLevels(){
        List<Scene> levels = new ArrayList<>();
        levels.add(new LevelOne());
        //levels.add(new LevelTwo());
        levels.add(new LevelThree());
        levels.add(new LevelFour());
        levels.add(new LevelFive());
        return levels;
    }

}
