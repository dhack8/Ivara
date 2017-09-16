package renderer;

import core.Game;
import core.scene.Entity;
import core.scene.Scene;
import maths.Vector;
import org.junit.Test;
import processing.core.PApplet;
import pxljam.TheLegend27;

/**
 * Created by dhack on 16-Sep-17.
 */
public class OldRendererTest {

    OldRenderer testOldRenderer;

    @Test
    public void draw() throws Exception {
        PApplet.main("App");

        Scene scene = new Scene() {};
        Entity testEntity = new Entity(new Vector(1, 1)) {};
        scene.addEntity(testEntity);

        Game game = new TheLegend27();
        game.setCurrentScene(scene);

        testOldRenderer = new OldRenderer(game);

        while(true){

        }
    }
}