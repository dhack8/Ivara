package ivara.entities.scripts;

import core.Script;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.InputHandler;
import core.input.SensorHandler;
import core.struct.Sensor;
import core.struct.Timer;
import maths.Vector;

/**
 * This script allows an entity to chase an enemy, and patrol when it's too far away from the home
 * Created by Alex Mitchell on 9/10/2017.
 */
public class ChaseScript implements Script {
    private GameEntity entity;
    private GameEntity toChase;

    private boolean chasing;
    private boolean home;

    private final Vector homePos;

    private final float DISTANCE = 8f;

    private final float CHASE_SPEED = 1.2f; // in ms^-1
    private final float RETREAT_SPEED = 2f; // in ms^-1
    private final float PATROL_SPEED = 1.5f; // in ms^-1

    private Vector p1; // the patrol positions
    private Vector p2;

    /**
     * This script chases a target and patrols a "home" location when not chasing
     * @param entity The entity using the script
     * @param toChase The entity to chase
     */
    public ChaseScript(GameEntity entity, GameEntity toChase){
        this.entity = entity;
        this.toChase = toChase;
        chasing = false;
        home = true;

        homePos = new Vector(entity.getTransform());
        p1 = new Vector(homePos.x -2.5f, homePos.y); // temporarily, the patrol positions are +-2.5f on the x plane in relation to the home location
        p2 = new Vector(homePos.x +2.5f, homePos.y);
    }

    @Override
    public void update(int dt, GameEntity entity) {
        Vector targetPos = toChase.getTransform();

        float dx = targetPos.x-homePos.x;
        float dy = targetPos.y-homePos.y;

        float distance = (float)(Math.sqrt((dx*dx) + (dy*dy))); // float conversion shouldn't matter too much

        chasing = distance < DISTANCE; // chasing if player makes it within the radius of home
        home = (home || nearPoint(homePos)) && !chasing; // at home if we were home or we are near home AND we aren't chasing

        if(chasing){
            chase();
        }else if(home){
            patrol();
        }else{
            home();
        }
    }

    /**
     * Checks whether a given position is close to a point (rounding errors are preventing exact checks)
     * @param position The position to check regarding the entity's position
     * @return Whether the point is near the entity
     */
    private boolean nearPoint(Vector position){
        float threshold = 0.1f;

        Vector location = entity.getTransform();

        if(location.x > position.x-threshold && location.x < position.x + threshold){
            if(location.y > position.y-threshold && location.y < position.y + threshold){
                return true;
            }
        }
        return false;
    }


    /**
     * Sets the entity's velocity such that it seeks out the target entity
     */
    private void chase(){
        VelocityComponent vComp = entity.get(VelocityComponent.class).get();
        Vector target = toChase.getTransform();
        Vector from = entity.getTransform();
        vComp.set(getV(target, from, CHASE_SPEED));
    }

    /**
     * Moves the entity to a home location
     */
    private void home(){
        VelocityComponent vComp = entity.get(VelocityComponent.class).get();
        vComp.set(getV(homePos, entity.getTransform(),RETREAT_SPEED));
    }

    /**
     * Moves the entity between two specified points once it reaches home
     */
    private void patrol(){
        VelocityComponent vComp = entity.get(VelocityComponent.class).get();

        if(nearPoint(p1)){ // cheeky swap for now
            Vector temp = p1;
            p1 = p2;
            p2 = temp;
        }

        vComp.set(getV(p1, entity.getTransform(),PATROL_SPEED));
    }

    /**
     * Calculates and returns a vector representing the x and y velocities.
     * These are used to move the entity towards a target
     * @param target The target position
     * @param from The start position
     * @return A vector representing velocity
     */
    private Vector getV(Vector target, Vector from, float Speed){
        float dx = target.x - from.x;
        float dy = target.y - from.y;
        double angle = Math.atan(dx/dy);
        float xVel = (float)(Speed * Math.sin(angle));
        float yVel = (float)(Speed * Math.cos(angle));

        if(dy <0){xVel *= -1; yVel *= -1;}
        return new Vector(xVel,yVel);
    }

}
