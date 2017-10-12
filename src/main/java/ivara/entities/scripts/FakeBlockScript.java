package ivara.entities.scripts;

import core.Script;
import core.components.PhysicsComponent;
import core.components.SensorHandlerComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.Sensor;
import maths.Vector;
import physics.PhysicProperties;

/**
 * Created by David Hack Local on 12-Oct-17.
 */
public class FakeBlockScript implements Script{

    Sensor top;
    Sensor bot;
    boolean alive;

    public FakeBlockScript(Sensor top, Sensor bot){
        this.top = top;
        this.bot = bot;
        alive = true;
    }

    @Override
    public void update(int dt, GameEntity entity) {
        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

        if(sensorHandler.isActive(top) && alive){
            entity.addComponent(new PhysicsComponent(entity, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));
            alive = false;
        }

        if(sensorHandler.isActive(bot) && !alive){
            GameEntity collided = sensorHandler.getActivatingEntities(bot).stream().findAny().get();
            VelocityComponent v = entity.get(VelocityComponent.class).get();

            Vector c = collided.get(VelocityComponent.class)
                    .map(VelocityComponent::getVelocity)
                    .orElse(new Vector(0, 0));

            v.set(c);
        }
    }
}
