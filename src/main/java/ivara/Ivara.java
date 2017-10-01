package ivara;

import backends.InputBroadcaster;
import backends.Renderer;
import backends.processing.PWindow;
import core.Game;
import ivara.scenes.LevelThree;
import processing.core.PApplet;
import ivara.scenes.LevelOne;

/**
 * Created by Callum Li on 9/15/17.
 */
public class Ivara extends Game {

    public Ivara(Renderer renderer, InputBroadcaster inputBroadcaster) {
        //super(new LevelOne(), renderer, inputBroadcaster);
        super(new LevelThree(), renderer, inputBroadcaster);
    }

    public static void main(String[] args) {
        PWindow processingBackend = new PWindow();
        PApplet.runSketch(new String[]{"PWindow"}, processingBackend);
        Game g = new Ivara(processingBackend, processingBackend);
        g.start();
    }
}
