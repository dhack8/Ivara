package ivara.entities.scripts;

import core.Script;
import core.components.SensorHandlerComponent;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.Sensor;
import ivara.entities.PlayerEntity;

/**
 * Created by Alex Mitchell on 3/10/2017.
 */
public class LevelChangeScript implements Script{
    private final Sensor sensor;

    private boolean entered = false; // Todo sort out a way to reset a scene
    /**
     * Create a LevelChangeScript
     */
    public LevelChangeScript(Sensor s){
        this.sensor = s;
    }

    @Override
    public void update(int dt, GameEntity entity) {
        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

        if(sensorHandler.isActive(sensor)){
            //GameEntity collided = sensorHandler.getActivatingEntities(sensor).stream().filter((e) -> e instanceof PlayerEntity).findAny().get();
            //boolean playerCollision = sensorHandler.getActivatingEntities(sensor).stream().filter((e) -> e instanceof PlayerEntity).findAny().isPresent();
            boolean playerCollision = sensorHandler.getActivatingEntities(sensor).stream().anyMatch((e) -> e instanceof PlayerEntity);

            if(!entered && playerCollision) {
                entered = true;
                entity.getScene().getGame().nextScene();
            }
        }
    }
}