package ivara.entities.scripts;

import core.Script;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import maths.Vector;

/**
 * The PatrolScript causes the entity to patrol it's home location by moving back and forth, deviating by a given deviance.
 * @author Alex Mitchell
 */
public class PatrolScript implements Script{

    private Vector pos1; // Positions to patrol
    private Vector pos2;
    private float speed; // Speed at which it patrols

    private Vector target; // The current position to track to
    private static final float THRESHOLD = 0.01f; // The threshold used to check if the entity is at a target position

    /**
     * Creates a Script that causes the entity to patrol between two points.
     * @param entity The game entity.
     * @param deviance The x and y deviance from the origin.
     * @param speed The speed at which the entity moves between the two targets.
     */
    public PatrolScript(GameEntity entity, Vector deviance, float speed){
        Vector home = new Vector(entity.getTransform());
        pos1 = new Vector(home.x - deviance.x, home.y - deviance.y);
        pos2 = new Vector(home.x + deviance.x, home.y + deviance.y);

        target = pos2;
        this.speed = speed;

        updateVelocity(entity);
    }


    @Override
    public void update(int dt, GameEntity entity) {
        Vector current = entity.getTransform();
        if(atPoint(current, target)){swapTarget();updateVelocity(entity);}
    }

    /**
     * Updates the velocity of the entity with the target position.
     * @param entity The entity to update.
     */
    private void updateVelocity(GameEntity entity){
        VelocityComponent velocityComponent = entity.get(VelocityComponent.class).get();
        Vector velocity = target.sub(entity.getTransform()).norm();
        velocity.scaleBy(speed);
        velocityComponent.set(velocity);
    }

    /**
     * Swaps between the two target locations.
     */
    private void swapTarget(){
        target = (target.equals(pos1)? pos2 : pos1);
    }

    /**
     * Checks whether two given points "equal" eachother with a given threshold for rounding issues.
     * @param location The first location.
     * @param point The second location.
     * @return Whether the points are within a close enough proximity to consider as being equal.
     */
    private boolean atPoint(Vector location, Vector point){
        return location.dist(point) <= THRESHOLD;
    }

}
