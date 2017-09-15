package core.scene;

import maths.Vector;

/**
 * This class represents the entities within the game
 * @author Alex Mitchell
 */
public abstract class Entity {

    public Vector position;

    /**
     * Translate the entity
     * @param dx Position change in the X plane
     * @param dy Position change in the Y plane
     */
    public void translate(float dx, float dy){
        position.add(dx, dy);
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }
}
