package pxljam;

import backends.InputBroadcaster;
import backends.Renderer;
import backends.processing.PWindow;
import core.Game;
import processing.core.PApplet;
import pxljam.scenes.FirstScene;

/**
 * Created by Callum Li on 9/15/17.
 */
public class TheLegend27 extends Game {

    public TheLegend27(Renderer renderer, InputBroadcaster inputBroadcaster) {
        super(new FirstScene(), renderer, inputBroadcaster);
    }

    public static void main(String[] args) {
        PWindow processingBackend = new PWindow();
        PApplet.runSketch(new String[]{"Sketch Demo"}, processingBackend);
        Game g = new TheLegend27(processingBackend, processingBackend);
        g.start();
    }
}
