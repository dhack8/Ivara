package core.entity;

import core.components.TransformComponent;
import eem.Entity;
import maths.Vector;

/**
 * This class represents the entities within the game
 * @author Alex Mitchell
 */
public abstract class GameEntity extends Entity {

    public GameEntity(Vector transform) {
        addComponent(new TransformComponent(this, transform));
    }

    public Vector getPosition() {
        return get(TransformComponent.class).getTransform();
    }
}
