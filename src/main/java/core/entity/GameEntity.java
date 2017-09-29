package core.entity;

import eem.Entity;
import maths.Vector;

/**
 * This class represents the entities within the game
 * @author Alex Mitchell
 */
public abstract class GameEntity extends Entity {

    private final Vector transform;

    public Vector getTransform() {
        return transform;
    }

    public GameEntity(Vector transform) {
        this.transform = transform;
    }

}
