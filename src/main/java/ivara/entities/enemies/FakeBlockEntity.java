package ivara.entities.enemies;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.input.SensorHandler;
import core.struct.*;
import ivara.entities.PlayerEntity;
import ivara.entities.Removable;
import ivara.entities.scripts.BasicEnemyScript;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This class handles the creation and actions of a FakeBlockEntity.
 * The fake block will fall if a player comes into contact with the top.
 * @author David Hack
 */
public class FakeBlockEntity extends GameEntity implements Removable {

    // Constants
    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1f;
    private final static float SENSOR_PADDING = 0.05f;
    private final static float SENSOR_HEIGHT = 0.1f;

    private final Vector intialLoc;
    private final AnimatedSprite fbs;

    public FakeBlockEntity(Vector transform){
        this("plains", transform);
    }

    public FakeBlockEntity(String theme, Vector transform){
        super(transform);
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
        SpriteComponent sc = new SpriteComponent(this);
        fbs = new FakeBlockSprite(theme, dimension);
        sc.add(fbs);
        addComponent(sc);

        // Sensors
        Vector SensorTopLeft = new Vector(SENSOR_PADDING, 0);
        Vector sensorDimension = new Vector(dimension.x-SENSOR_PADDING*2, SENSOR_HEIGHT);
        Sensor top = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, SensorTopLeft, sensorDimension));
        SensorTopLeft = new Vector(SENSOR_PADDING, dimension.y-SENSOR_HEIGHT);
        Sensor bot = new Sensor(new AABBCollider(AABBCollider.MIN_DIM, SensorTopLeft, sensorDimension));
        addComponent(new SensorComponent(this, new Sensor[]{top, bot}));

        // Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        // Scripts
        addComponent(new ScriptComponent(this, new FakeBlockScript(top, bot)));
    }

    /**
     * Handles the Sprite displayed by the FakeBlockEntity.
     * @author David Hack
     */
    private class FakeBlockSprite extends AnimatedSprite {
        /**
         * Constructs a new FakeBlockSprite that changes sprites dependant on the current state of the entity.
         * @param dimensions The size.
         */
        private FakeBlockSprite(String theme, Vector dimensions){
            super(new Vector(0,0), dimensions, 10000);

            String state = "normal";
            String[] resources = new String[]{
                    theme + "-fake-block"
            };
            addResources(state, Arrays.asList(resources));
            state = "dead";
            resources = new String[]{
                    theme + "-fake-block-dead"
            };
            addResources(state, Arrays.asList(resources));

            setState("normal");
        }
    }

    /**
     * A FakeBlockScript handles the behaviour of the FakeBlock entity.
     * @author David Hack
     */
    private class FakeBlockScript implements Script {

        // Constants
        private static final int FALL_DELAY = 500;
        private static final int REMOVE_DELAY = 1000;

        private Sensor top;
        private Sensor bot;
        private boolean alive;

        /**
         * Constructs a FakeBlockScript with the two sensors of the entity.
         * Sets the alive flag to true. The sprite updates on this flag.
         * @param top The top sensor.
         * @param bot The bottom sensor.
         */
        public FakeBlockScript(Sensor top, Sensor bot){
            this.top = top;
            this.bot = bot;
            alive = true;
        }

        @Override
        public void update(int dt, GameEntity entity) {
            SensorHandler sensorHandler = entity.get(SensorHandlerComponent.class).get().getSensorHandler();

            if(sensorHandler.isActive(top) && alive){
                if(sensorHandler.getActivatingEntities(top).stream().anyMatch((e) -> e instanceof PlayerEntity)) { // If the player hits the top of a block, the block dies.
                    alive = false;

                    fbs.setState("dead");
                    entity.getScene().addTimer(new Timer(FALL_DELAY, (Runnable & Serializable)() -> {
                        entity.addComponent(new PhysicsComponent(entity, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));
                    }));

                    entity.getScene().addTimer(new Timer(REMOVE_DELAY, (Runnable & Serializable)() -> {
                        doRemove();
                    }));
                }
            }

            if(sensorHandler.isActive(bot) && !alive){
                doRemove();
            }
        }

        /**
         * Removes the block from the scene.
         */
        public void doRemove(){
            if(alive) return;
            getScene().removeEntity(FakeBlockEntity.this);
            removeComponent(PhysicsComponent.class);
            getTransform().setAs(intialLoc);
            get(VelocityComponent.class).get().getVelocity().setAs(0f,0f);
            fbs.setState("normal");
            alive = true;
        }
    }
}
