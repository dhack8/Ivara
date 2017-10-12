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
import util.Debug;

/**
 * This script allows an entity to chase an enemy, and patrol when it's too far away from the home
 * Created by Alex Mitchell on 9/10/2017.
 * @author David Hack
 * @author Alex Mitchell
 */
public class ChaseScript implements Script {
    private GameEntity entity;
    private GameEntity targetEntity;

    private final Vector homePos;

    private static final float DETECTION_RADIUS = 5f;
    private static final float HOME_RADIUS = 10f;

    private static final float SPEED = 1.2f;

    private static final float THRESHOLD = 1.2f; // could overshoot

    private static final float PATH_SIZE = 7f; // distance patrolled from home loaation

    private Vector p1; // the patrol positions
    private Vector p2;
    private Vector patrolTarget;

    private Vector desiredPos;



    /**
     * This script chases a target and patrols a "home" location when not chasing
     * @param entity The entity using the script
     * @param targetEntity The entity to chase
     */
    public ChaseScript(GameEntity entity, GameEntity targetEntity){
        this.entity = entity;
        this.targetEntity = targetEntity;

        homePos = new Vector(entity.getTransform());
        p1 = new Vector(homePos.x -PATH_SIZE/2, homePos.y); // temporarily, the patrol positions are +-2.5f on the x plane in relation to the home location
        p2 = new Vector(homePos.x +PATH_SIZE/2, homePos.y);

        desiredPos = patrolTarget = p2;
    }

    @Override
    public void update(int dt, GameEntity entity) {
        Vector targetPos = targetEntity.getTransform();
        Vector entityPos = entity.getTransform();

        if(targetPos.dist(entityPos) < DETECTION_RADIUS){ // if to chase detected
            if(targetPos.dist(homePos) > HOME_RADIUS){ // if outside of home
                Vector homeToChase = targetPos.sub(homePos).norm();
                homeToChase.scaleBy(HOME_RADIUS);
                desiredPos = homeToChase.add(homePos);
            }else{ // if inside home
                desiredPos = targetPos;
            }
        }else{ // if the player is not within the detection radius
            if(atPoint(entityPos, patrolTarget)) switchPatrolTarget();
            desiredPos = patrolTarget;
        }

        Vector velocity = desiredPos.sub(entityPos).norm();
        velocity.scaleBy(SPEED);
        entity.get(VelocityComponent.class).get().set(velocity);
    }

    private void switchPatrolTarget(){
        patrolTarget = (patrolTarget.equals(p1))? p2:p1;
    }

    private boolean atPoint(Vector location, Vector point){
        return location.dist(point) <= THRESHOLD;
    }
}
