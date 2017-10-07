package ivara.scripts;

import core.Script;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.Sensor;
import maths.Vector;

/**
 * This script inverts the velocity of an enemy entity when there is a left/right collision or no floor tile
 * @author Alex Mitchell
 */
public class BasicEnemyScript implements Script{

    private Sensor left; // checking if the left is obstructed
    private Sensor right; // checking if the right is obstructed
    private Sensor bottom; // collision on bottom

    private Sensor bLeft; // Checking if a block exists where the entity is about to walk (to the left)
    private Sensor bRight; // Checking if a block exists where the entity is about to walk (to the right)

    public BasicEnemyScript(Sensor left, Sensor right, Sensor bottom){
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    public BasicEnemyScript(Sensor left, Sensor right, Sensor bottom, Sensor bLeft, Sensor bRight){
        this.left = left;
        this.right = right;
        this.bottom = bottom;

        this.bLeft = bLeft;
        this.bRight = bRight;
    }

    @Override
    public void update(int dt, GameEntity entity) {
        VelocityComponent vComp = entity.get(VelocityComponent.class).get();
        Vector velocity = vComp.getVelocity();
        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

        if (sensorHandler.isActive(right)) { // if collision on either side
            GameEntity collided = sensorHandler.getActivatingEntities(right).stream().findAny().get(); // todo use this later for checking if player collision?
            float inside = right.collider.getAABBBoundingBox().getDimension().x;
            vComp.setX(velocity.x*-1);
            entity.transform.add(-inside, 0);
        }else if (sensorHandler.isActive(left)) { // if collision on either side
            GameEntity collided = sensorHandler.getActivatingEntities(left).stream().findAny().get(); // todo use this later for checking if player collision?
            float inside = left.collider.getAABBBoundingBox().getDimension().x;
            vComp.setX(velocity.x*-1);
            entity.transform.add(inside, 0);
        }else if(bLeft != null && bRight != null){ // Todo: Temporary
            if(!sensorHandler.isActive(bLeft)){
                vComp.setX(velocity.x*-1);
            }else if(!sensorHandler.isActive(bRight)){
                vComp.setX(velocity.x*-1);
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

}
