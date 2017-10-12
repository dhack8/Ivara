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
 * @author David Hack
 * @author Alex Mitchell
 */
public class ChaseScript implements Script {
    private GameEntity entity;
    private GameEntity toChase;

    private final Vector homePos;

    private final float DISTANCE = 8f;

    private final float CHASE_SPEED = 1.2f; // in ms^-1
    private final float RETREAT_SPEED = 1.2f; // in ms^-1
    private final float PATROL_SPEED = 1.2f; // in ms^-1

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

        homePos = new Vector(entity.getTransform());
        p1 = new Vector(homePos.x -2.5f, homePos.y); // temporarily, the patrol positions are +-2.5f on the x plane in relation to the home location
        p2 = new Vector(homePos.x +2.5f, homePos.y);
    }

    @Override
    public void update(int dt, GameEntity entity) {

    }
}
