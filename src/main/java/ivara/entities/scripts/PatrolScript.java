package ivara.entities.scripts;

import core.Script;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.struct.Timer;
import maths.Vector;

/**
 * Created by Alex Mitchell on 12/10/2017.
 */
public class PatrolScript implements Script{

    private Vector home;
    private Vector pos1;
    private Vector pos2;
    private float speed;

    /**
     * Creates a Script that causes the entity to patrol their initial spawn location
     * @param entity The game entity
     * @param deviance The x and y deviance from the origin
     */
    public PatrolScript(GameEntity entity, Vector deviance, float speed){
        home = new Vector(entity.getTransform());
        pos1 = new Vector(home.x - deviance.x, home.y - deviance.y);
        pos2 = new Vector(home.x + deviance.x, home.y + deviance.y);
        this.speed = speed;
    }

    @Override
    public void update(int dt, GameEntity entity) {
        if(pos1.equals(home))return;

        VelocityComponent vComp = entity.get(VelocityComponent.class).get();
        
        if(nearPoint(pos1, entity.getTransform())){ // cheeky swap for now
            Vector temp = pos1;
            pos1 = pos2;
            pos2 = temp;
        }

        vComp.set(getV(pos1, entity.getTransform()));
    }

    /**
     * Calculates and returns a vector representing the x and y velocities.
     * These are used to move the entity towards a target
     * @param target The target position
     * @param from The start position
     * @return A vector representing velocity
     */
    private Vector getV(Vector target, Vector from){
        float dx = target.x - from.x;
        float dy = target.y - from.y;
        double angle = Math.atan(dx/dy);
        float xVel = (float)(speed * Math.sin(angle));
        float yVel = (float)(speed * Math.cos(angle));

        if(dy <0){xVel *= -1; yVel *= -1;}
        return new Vector(xVel,yVel);
    }

    /**
     * Checks whether a given position is close to a point (rounding errors are preventing exact checks)
     * @param position The position to check regarding the entity's position
     * @return Whether the point is near the entity
     */
    private boolean nearPoint(Vector position, Vector current){
        float threshold = 0.1f;

        if(current.x > position.x-threshold && current.x < position.x + threshold){
            if(current.y > position.y-threshold && current.y < position.y + threshold){
                return true;
            }
        }
        return false;
    }
}
