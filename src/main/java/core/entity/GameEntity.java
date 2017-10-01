package core.entity;

import core.input.InputHandler;
import core.scene.Scene;
//import scew.Entity;
import maths.Vector;
import scew.Entity;

/**
 * This class represents the entities within the game
 * @author Alex Mitchell
 */
public abstract class GameEntity extends Entity {

    public final Vector transform;
    private Scene scene;

    public Vector getTransform() {
        return transform;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public InputHandler getInput() {
        return scene.getGame().inputHandler;
    }

    public GameEntity(Vector transform) {
        this.transform = transform;
    }

}
