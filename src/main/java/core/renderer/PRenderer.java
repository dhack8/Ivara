package core.renderer;

import core.Game;
import core.components.SpriteComponent;
import core.scene.Camera;
import core.scene.Entity;
import core.scene.Scene;
import maths.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class that will render the given scene when asked to.
 *
 * @author David Hack
 */
public class PRenderer {

    private Game game;

    private void render(Game game) {
        this.game = game;
    }

    public void draw(Scene scene){
        Collection<Entity> entities = new ArrayList<>();

        Camera camera = scene.getCamera();
        Vector cameraLoc = camera.getLocation();

        game.scale(camera.getScale());
        game.translate(-cameraLoc.x, -cameraLoc.y);

        for(Entity e : entities){
            Vector position = e.getPosition();
            game.color(0,0,0);
            game.rect(position.x*100, position.y*100, 100, 100);
        }
    }
}
