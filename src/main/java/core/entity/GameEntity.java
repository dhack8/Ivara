package core.entity;

import core.input.InputHandler;
import core.scene.Scene;
import scew.Entity;
import maths.Vector;

/**
 * This class represents the entities within the game
 * @author Alex Mitchell
 */
public abstract class GameEntity extends Entity {

    public final Vector transform;
    private Scene scene;
    private InputHandler input;


    public Vector getTransform() {
        return transform;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setInput(InputHandler input) {
        this.input = input;
    }

    public InputHandler getInput() {
        return input;
    }

    public GameEntity(Vector transform) {
        this.transform = transform;
    }

}
