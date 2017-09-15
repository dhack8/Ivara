package core.renderer;

import core.Game;
import core.components.SpriteComponent;
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
    private Scene scene;

    private void render(Scene scene, Game game) {
        this.game = game;
        this.scene = scene;
    }

    public void draw(){
        Collection<Entity> entities = new ArrayList<>();

        for(Entity e : entities){
            Vector position = e.getPosition();
            game.color(0,0,0);
            game.rect(position.x*100, position.y*100, 100, 100);
        }
    }
}
