package ivara.entities.scripts;

import core.Script;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.Sensor;
import core.struct.Timer;

/**
 * This script inverts the velocity of an enemy entity when there is a left/right collision or no floor tile
 * @author Alex Mitchell
 */
public class BasicEnemyScript implements Script{

    private int pauseTime = 200;

    private Sensor left; // checking if the left is obstructed
    private Sensor right; // checking if the right is obstructed
    private Sensor bottom; // collision on bottom

    private Sensor bLeft; // Checking if a block exists where the entity is about to walk (to the left)
    private Sensor bRight; // Checking if a block exists where the entity is about to walk (to the right)

    private boolean goingLeft;

    public BasicEnemyScript(GameEntity entity, int pauseTime, Sensor left, Sensor right, Sensor bottom){
        this.left = left;
        this.right = right;
        this.bottom = bottom;

        goingLeft = entity.get(VelocityComponent.class).get().getVelocity().x < 0;
    }

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

        if (sensorHandler.isActive(right) && !goingLeft) { // if collision on either side
            hitOnRight(vc, entity);
        }else if (sensorHandler.isActive(left) && goingLeft) { // if collision on either side
            hitOnLeft(vc, entity);
        }else if(bLeft != null && bRight != null){ // Todo: Temporary
            if(!sensorHandler.isActive(bLeft) && goingLeft){
                hitOnLeft(vc, entity);
            }else if(!sensorHandler.isActive(bRight) && !goingLeft){
                hitOnRight(vc, entity);
            }
        }

        /** Todo: If there is a physics component, a sensor must be used to counter the increasing Y velocity
         if(sensorHandler.isActive(bottom)){
            System.out.println("active");
            VelocityComponent vComp = entity.get(VelocityComponent.class).get();
            Vector velocity = vComp.getVelocity();
            vComp.getVelocity().set(velocity.x, 0f);
        }
         **/

    }

    public void setPauseTime(int time){
        pauseTime = time;
    }

    private void hitOnLeft(VelocityComponent vc, GameEntity entity){
        vc.setX(vc.getVelocity().x*-1);
        pause(vc, entity);

        goingLeft = false;
    }

    private void hitOnRight(VelocityComponent vc, GameEntity entity){
        vc.setX(vc.getVelocity().x*-1);
        pause(vc, entity);

        goingLeft = true;
    }

    private void pause(VelocityComponent vc, GameEntity entity){
        if(vc.isPaused()){return;}
        vc.pause();
        entity.getScene().addTimer(new Timer(pauseTime, () -> vc.unpause()));
    }
}
