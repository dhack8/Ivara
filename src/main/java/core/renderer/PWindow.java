package core.renderer;

import core.Game;
import core.scene.Camera;
import core.scene.Entity;
import core.scene.Scene;
import maths.Vector;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class that will render the given scene when asked to.
 *
 * @author David Hack
 */
public class PWindow extends PApplet implements Renderer{

    private Game game;
    private int x;
    private int y;

    public PWindow(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void settings(){
        size(x, y);
        noLoop();
    }

    public void render(Scene scene){
        Collection<Entity> entities = new ArrayList<>();

        Camera camera = scene.getCamera();
        Vector cameraLoc = camera.getLocation();

        scale(camera.getScale());
        translate(-cameraLoc.x, -cameraLoc.y);

        for(Entity e : entities){
            //Collection<PSpriteComponent> sprite = e.getComponents(PSpriteComponent.class);

            Vector position = e.getPosition();

            color(0,0,0);
            rect(position.x, position.y, 1, 1);
        }
    }
}
