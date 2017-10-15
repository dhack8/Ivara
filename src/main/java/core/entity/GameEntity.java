package core.entity;

import core.input.InputHandler;
import core.scene.Scene;
//import scew.Entity;
import maths.Vector;
import scew.Entity;

/**
 * This class represents the entities within the game.
 * It extends entity with a transform/location and a parent scene
 * @author Alex Mitchell
 */
public abstract class GameEntity extends Entity {

    public final Vector transform;
    private Scene scene;

    /**
     * Constructs a new game entity, with a transform, required for there is no default constructor.
     * @param transform transform/location of entity
     */
    public GameEntity(Vector transform) {
        this.transform = transform;
    }

    /**
     * Getter for the transform vector.
     * @return transform vector
     */
    public Vector getTransform() {
        return transform;
    }

    /**
     * Setter for the Scene, mainly used internally to assign entities their scene.
     * @param scene scene that contains this entity
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Getter for the scene.
     * @return scene that contains this entity
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Getter for the the input frame, how entities can query user input.
     * @return input frame to perform queries on
     */
    public InputHandler.InputFrame getInput() {
        return scene.getGame().getInputFrame();
    }
}
