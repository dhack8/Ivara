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
 * This script floats aimlessly and then charges at an entity's last position
 * Created by Alex Mitchell on 9/10/2017.
 */
public class ChargeScript implements Script {
    private GameEntity thisEntity;
    private GameEntity toChase;
    private boolean chasing;
    private Timer t;

    private final float SPEED = 3f; // in ms^-1
    private final int CHASE_TIME = 1000; // time (in ms) spent chasing in a single direction
    private final int WAIT_TIME = 500; // time (in ms) spent stationary

    /**
     * This script "chases" a target entity with brief pauses
     * @param thisEntity The entity using the script
     * @param toChase The entity to chase
     */
    public ChargeScript(GameEntity thisEntity, GameEntity toChase){
        this.thisEntity = thisEntity;
        this.toChase = toChase;
        chasing = false;
        t = new Timer(0, ()->{}); // initial timer
    }

    @Override
    public void update(int dt, GameEntity entity) {
        if(t.isFinished()){
            t = new Timer(
                    (chasing)?CHASE_TIME:WAIT_TIME,
                    ()->{
                        if(!chasing){
                            chasing = true;
                            chase();
                        }else{
                            chasing = false;
                            hover();
                        }
                    }
            );
            entity.getScene().addTimer(t);
        }
    }

    /**
     * Sets the entity's velocity such that it seeks out the target entity
     */
    private void chase(){
        VelocityComponent vComp = thisEntity.get(VelocityComponent.class).get();
        Vector target = toChase.getTransform();
        Vector from = thisEntity.getTransform();
        vComp.set(getV(target, from));
    }

    /**
     * Sets the entity's velocity to nothing
     */
    private void hover(){
        VelocityComponent vComp = thisEntity.get(VelocityComponent.class).get();
        vComp.set(new Vector(0f,0f));
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
        float xVel = (float)(SPEED * Math.sin(angle));
        float yVel = (float)(SPEED * Math.cos(angle));

        if(dy <0){xVel *= -1; yVel *= -1;}
        return new Vector(xVel,yVel);
    }
}
