package core.components;

import core.entity.GameEntity;
import scew.Component;
import maths.Vector;

/**
 * Holds the velocity of an GameEntity.
 *
 * @author Will Pearson & Others
 */
public class VelocityComponent extends Component<GameEntity> {

    private final Vector velocity;
    private boolean paused = false;

    /**
     * Constructs a new velocity component with a 0,0 velocity.
     * @param entity parent of the component
     */
    public VelocityComponent(GameEntity entity) {
        super(entity);
        this.velocity = new Vector(0, 0);
    }

    /**
     * Constructs a new velocity component with a given velocity.
     * @param entity parent of the component
     * @param velocity the desired initial velocity
     */
    public VelocityComponent(GameEntity entity, Vector velocity) {
        super(entity);
        this.velocity = new Vector(velocity);
    }

    /**
     * Changes the velocity by the set amount.
     * @param dx deltax
     * @param dy deltay
     */
    public void add(float dx, float dy) {
        velocity.incrementBy(dx, dy);
    }

    /**
     * Gets velocity.
     * @return velocity
     */
    public Vector getVelocity() {
        return  paused? new Vector(0,0) : velocity;
    }

    /**
     * Setter for the velocity.
     * @param velocity velocity
     */
    public void set(Vector velocity) {
        this.velocity.setAs(velocity);
    }

    /**
     * Setter for the X part of the velocity vector
     * @param x x value
     */
    public void setX(float x) { // Todo change how this is done
        velocity.setAs(x,velocity.y);
    }

    /**
     * Setter for the Y part of the velocity vector
     * @param y y value
     */
    public void setY(float y) { // Todo change how this is done :)
        velocity.setAs(velocity.x,y);
    }

    /**
     * Pauses the velocity is not updated by velocity system.
     */
    public void pause(){
        paused = true;
    }

    /**
     * Unpauses the velocity it is no updated by the velocity system.
     */
    public void unpause(){
        paused = false;
    }

    /**
     * Returns the boolean defining pauses status.
     * @return the pause boolean
     */
    public boolean isPaused(){
        return paused;
    }
}
