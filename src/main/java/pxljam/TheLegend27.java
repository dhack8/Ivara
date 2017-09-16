package pxljam;

import backends.InputBroadcaster;
import backends.Renderer;
import backends.processing.PWindow;
import core.Game;
import core.scene.Entity;
import core.scene.Scene;
import maths.Vector;
import processing.core.PApplet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Callum Li on 9/15/17.
 */
public class TheLegend27 extends Game {

    public TheLegend27(Renderer renderer, InputBroadcaster inputBroadcaster) {
        super(new TestScene(), renderer, inputBroadcaster);
    }

    public static void main(String[] args) {
        PWindow processingBackend = new PWindow();
        PApplet.runSketch(new String[]{"Sketch Demo"}, processingBackend);
        Game g = new TheLegend27(processingBackend, processingBackend);
        g.start();
    }
}
