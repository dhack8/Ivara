package ivara.entities.enemies;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.ResourceID;
import core.struct.Sensor;
import core.struct.Sprite;
import core.struct.Timer;
import ivara.entities.PlayerEntity;
import ivara.entities.scripts.BasicEnemyScript;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;

/**
 * Created by David Hack Local on 12-Oct-17.
 */
public class FakeBlockEntity extends GameEntity{

    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1f;

    private float sensorPadding = 0.05f;
    private float sensorHeight = 0.1f;

    public FakeBlockEntity(Vector transform){
        super(transform);
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
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("fake-block"), topLeft, dimension));
        addComponent(sc);

        //Sensors---
        Vector SensorTopLeft = new Vector(sensorPadding, 0);
        Vector sensorDimension = new Vector(dimension.x-sensorPadding*2, sensorHeight);
        Sensor top = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, SensorTopLeft, sensorDimension));

        SensorTopLeft = new Vector(sensorPadding, dimension.y-sensorHeight);
        Sensor bot = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, SensorTopLeft, sensorDimension));

        addComponent(new SensorComponent(this, new Sensor[]{top, bot}));

        //Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        //Scripts---
        addComponent(new ScriptComponent(this, new FakeBlockScript(top, bot, sc, new Sprite(new ResourceID("fake-block-dead"), topLeft, dimension))));
    }

    private class FakeBlockScript implements Script {

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
                if(sensorHandler.getActivatingEntities(top).stream().anyMatch((e) -> e instanceof PlayerEntity)) {
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
}
