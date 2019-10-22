package ivara.entities.scripts;


import core.Script;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.Sensor;
import core.struct.Timer;
import ivara.entities.ArrowEntity;

import java.io.Serializable;

/**
 * This script inverts the velocity of an enemy entity when there is a left/right collision or no floor tile to it's left or right. *
 * @author David Hack
 * @author Alex Mitchell
 */
public class BasicEnemyScript implements Script {

    private static final int pauseTime = 200; // Time spent before turning

    private Sensor left; // checking if the left is obstructed
    private Sensor right; // checking if the right is obstructed
    private Sensor bottom; // collision on bottom

    private Sensor bLeft; // Checking if a block exists where the entity is about to walk (to the left)
    private Sensor bRight; // Checking if a block exists where the entity is about to walk (to the right)

    private boolean goingLeft; // Current movement direction

    /**
     * Constructs a BasicEnemyScript that takes an entity and makes it move left and right, until there is a block obstructing or no block underneath.
     * At this point, the entity reverses its velocity.
     * @param entity The entity that has the script.
     * @param left The sensor to the left of the entity.
     * @param right The sensor to the right of the entity.
     * @param bottom The sensor to the bottom of the entity.
     * @param bLeft The sensor to the bottom left of the entity.
     * @param bRight The sensor to the bottom right of the entity.
     */
    public BasicEnemyScript(GameEntity entity, Sensor left, Sensor right, Sensor bottom, Sensor bLeft, Sensor bRight){
        this.left = left;
        this.right = right;
        this.bottom = bottom;

        this.bLeft = bLeft;
        this.bRight = bRight;

        goingLeft = entity.get(VelocityComponent.class).get().getVelocity().x < 0;
    }

    @Override
    public void update(int dt, GameEntity entity) {
        VelocityComponent vc = entity.get(VelocityComponent.class).get();
        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

        if (activated(sensorHandler, right) && !goingLeft){ // if collision on either side
            collide(vc, entity);
        }else if (activated(sensorHandler, left) && goingLeft){ // if collision on either side
            collide(vc, entity);
        }else if(!activated(sensorHandler, bLeft) && goingLeft){ // if no collision bottom left
            collide(vc, entity);
        }else if(!activated(sensorHandler, bRight) && !goingLeft){ // if no collision bottom right
            collide(vc, entity);
        }
    }

    private boolean activated(SensorHandler sensorHandler, Sensor sensor) {
        return sensorHandler.getActivatingEntities(sensor).stream().anyMatch((e) -> !(e instanceof ArrowEntity));
    }

    /**
     * Changes the direction of the entity.
     * @param vc The velocity component of the entity.
     * @param entity The entity to adapt.
     */
    private void collide(VelocityComponent vc, GameEntity entity){
        vc.setX(vc.getVelocity().x*-1);
        pause(vc, entity);
        goingLeft = !goingLeft;
    }

    /**
     * Pauses the entity if it is at a turning point.
     * @param vc The velocity component of the entity
     * @param entity The entity
     */
    private void pause(VelocityComponent vc, GameEntity entity){
        if(vc.isPaused()){return;}
        vc.pause();
        if(entity.getScene() == null) return;
        entity.getScene().addTimer(new Timer(pauseTime, (Runnable & Serializable)() -> {
            vc.unpause();
        }));
    }
}
