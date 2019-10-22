package ivara.entities;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Sprite;
import ivara.entities.enemies.ImmortalEnemy;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;

import java.io.Serializable;

/**
 * This class handles the behaviour of the PushableBlockEntity
 * @author David Hack
 */
public class PushableBlockEntity extends GameEntity implements Removable{

    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1f;
    
    private final static float SENSOR_PADDING = 0.05f;
    private final static float SENSOR_HEIGHT = 0.1f;
    private final static float EXTRA_SENSOR_HEIGHT = 0.05f;

    private Vector intialLoc;
    private Sensor bot;

    public PushableBlockEntity(float x, float y) {
        this("plains", x, y);
    }

    public PushableBlockEntity(String theme, float x, float y){
        super(new Vector(x,y));
        intialLoc = new Vector(transform);
        Vector dimension = new Vector(WIDTH, HEIGHT);

        // Velocity
        VelocityComponent v = new VelocityComponent(this);
        v.set(new Vector(0f, 0f));
        addComponent(v);

        // Layer
        addComponent(new RenderComponent(this, 1000));

        // Collider
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        // Sprite
        addComponent(new SpriteComponent(this, new Sprite(new ResourceID(theme + "-middle"), new Vector(0,0), dimension)));

        // Sensor
        Vector sensorDimension = new Vector(dimension.x-SENSOR_PADDING*2, SENSOR_HEIGHT + EXTRA_SENSOR_HEIGHT);
        Vector SensorTopLeft = new Vector(SENSOR_PADDING, dimension.y-SENSOR_HEIGHT);
        bot = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, SensorTopLeft, sensorDimension));
        addComponent(new SensorComponent(this, bot));
        addComponent(new SensorHandlerComponent(this));

        // Physics
        addComponent(new PhysicsComponent(this, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));

        // Script
        addComponent(new ScriptComponent(this, new PushableBlockScript()));
    }

    /**
     * This class handles the movement and actions of the PushableBlockScript.
     * This block is able to be pushed by any any entity.
     * @author David Hack
     */
    private class PushableBlockScript implements Script{
        @Override
        public void update(int dt, GameEntity entity) {
            SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();
            if(sensorHandler.isActive(bot)){
                GameEntity collided = sensorHandler.getActivatingEntities(bot).stream().findAny().get();

                if(collided instanceof ImmortalEnemy){
                    getScene().removeEntity(entity);
                    entity.getTransform().setAs(intialLoc);
                    entity.get(VelocityComponent.class).get().getVelocity().setAs(0f,0f);
                    return;
                }

                VelocityComponent v = entity.get(VelocityComponent.class).get();

                Vector c = collided.get(VelocityComponent.class)
                        .map(VelocityComponent::getVelocity)
                        .orElse(new Vector(0, 0));

                v.set(c);
            }
        }
    }
}
