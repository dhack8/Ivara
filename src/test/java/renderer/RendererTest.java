package renderer;

import core.Game;
import core.scene.Entity;
import core.scene.Scene;
import maths.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import pxljam.TheLegend27;

import static org.junit.Assert.*;

/**
 * Created by dhack on 16-Sep-17.
 */
public class RendererTest {

    Renderer testRenderer;

    @Test
    public void draw() throws Exception {
        PApplet.main("App");

        Scene scene = new Scene() {};
        Entity testEntity = new Entity(new Vector(1, 1)) {};
        scene.addEntity(testEntity);

        Game game = new TheLegend27();
        game.setScene(scene);

        testRenderer = new Renderer(game);

        while(true){

        }
    }
}