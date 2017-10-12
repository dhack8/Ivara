package ivara.entities.scripts;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.Sensor;
import core.struct.Sprite;
import core.struct.Timer;
import maths.Vector;
import physics.PhysicProperties;

/**
 * Created by David Hack Local on 12-Oct-17.
 */
public class FakeBlockScript implements Script{

    private static final int FALL_DELAY = 500;
    private static final int REMOVE_DELAY = 3000;

    Sensor top;
    Sensor bot;
    SpriteComponent sc;
    Sprite s;
    boolean alive;

    public FakeBlockScript(Sensor top, Sensor bot, SpriteComponent sc, Sprite s){
        this.top = top;
        this.bot = bot;
        this.sc = sc;
        this.s = s;
        alive = true;
    }

    @Override
    public void update(int dt, GameEntity entity) {
        SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

        if(sensorHandler.isActive(top) && alive){
            alive = false;

            sc.clearSprites();
            sc.add(s);

            entity.getScene().addTimer(new Timer(FALL_DELAY, () -> {
                entity.addComponent(new PhysicsComponent(entity, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));
            }));

            entity.getScene().addTimer(new Timer(REMOVE_DELAY, () -> {
                entity.getScene().removeEntity(entity);
            }));
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
