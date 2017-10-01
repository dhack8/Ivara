package ivara;

import backends.InputBroadcaster;
import backends.Renderer;
import backends.processing.PWindow;
import com.jogamp.opengl.GLException;
import core.Game;
import ivara.scenes.LevelThree;
import processing.core.PApplet;
import ivara.scenes.LevelOne;
import ivara.scenes.IntroLevel;

/**
 * Created by Callum Li on 9/15/17.
 */
public class Ivara extends Game {

    public Ivara(Renderer renderer, InputBroadcaster inputBroadcaster) {
        //super(new LevelOne(), renderer, inputBroadcaster);
        super(new LevelThree(), renderer, inputBroadcaster);
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

        Game g = new Ivara(processingBackend, processingBackend);
        g.start();
    }
}
