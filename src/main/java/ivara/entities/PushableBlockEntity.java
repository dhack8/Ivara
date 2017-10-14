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

/**
 * Created by David Hack Local on 14-Oct-17.
 */
public class PushableBlockEntity extends GameEntity{

    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1f;

    private float sensorPadding = 0.05f;
    private float sensorHeight = 0.1f;

    private Vector intialLoc;

    private Sensor bot;

    public PushableBlockEntity(float x, float y){
        super(new Vector(x,y));
        intialLoc = new Vector(transform);
        Vector dimension = new Vector(WIDTH, HEIGHT);

        //Velocity---
        VelocityComponent v = new VelocityComponent(this);
        v.set(new Vector(0f, 0f));
        addComponent(v);

        //Layer---
        addComponent(new RenderComponent(this, 1000));

        //Collider--
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        //Sprite---
        addComponent(new SpriteComponent(this, new Sprite(new ResourceID("dirt"), new Vector(0,0), dimension)));

        //Sensor---
        Vector sensorDimension = new Vector(dimension.x-sensorPadding*2, sensorHeight);
        Vector SensorTopLeft = new Vector(sensorPadding, dimension.y-sensorHeight);
        bot = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, SensorTopLeft, sensorDimension));

        addComponent(new SensorComponent(this, bot));

        //Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        //Physics---
        addComponent(new PhysicsComponent(this, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));

        //Script---
        addComponent(new ScriptComponent(this, new PushableBlockScript()));
    }

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
