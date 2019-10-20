package ivara.entities;

import core.components.*;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import core.struct.Sensor;
import ivara.entities.scripts.CameraScript;
import ivara.entities.scripts.PlayerScript;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;

import java.util.*;

/**
 * This class handles the behaviour of the PlayerEntity in the game.
 * @author Alex Mitchell
 * @author David Hack
 */
public class PlayerEntity extends GameEntity {

    // Constants
    public static final float WIDTH = 1f;
    public static final float HEIGHT = 1.5f;

    private static final float WIDTH_OFF = 0.35f;
    private static final float HEIGHT_OFF = 0.3f;

    private static final float JUMP_SENSOR_HEIGHT = 0.15f;
    private static final float JUMP_SENSOR_EXTRA = 0.01f;
    private static final float ANTI_WALL_RUN = 0.1f; // 0 for wall running

    //PLAYER STATES
    public final static String WALK_RIGHT = "walk-right";
    public final static String WALK_LEFT = "walk-left";
    public final static String IDLE_RIGHT = "idle-right";
    public final static String IDLE_LEFT = "idle-left";
    public final static String JUMP_RIGHT = "jump-right";
    public final static String JUMP_LEFT = "jump-left";
    public final static String DUCK_RIGHT = "duck-right";
    public final static String DUCK_LEFT = "duck-left";
    public final static String SHOT_RIGHT = "shot-right";
    public final static String SHOT_LEFT = "shot-left";


    public static Set<GameEntity> COLLECTIBLE_ENTITIES = new HashSet<>();
    public static Set<GameEntity> USED_COLLECTIBLE_ENTITIES = new HashSet<>();

    public static Map<String, Float> ITEM_FLAGS = new HashMap<>();

    /**
     * Creates a PlayerEntity at a given position
     *
     * @param x The x position
     * @param y The y position
     */
    public PlayerEntity(float x, float y) {
        super(new Vector(x, y));

        // Velocity
        VelocityComponent v = new VelocityComponent(this);
        addComponent(v);

        // Sprites
        SpriteComponent sc = new SpriteComponent(this);
        PlayerSprite playerSprite = new PlayerSprite(new Vector(0,0), new Vector(WIDTH, HEIGHT), 160);
        sc.add(playerSprite);
        addComponent(sc);

        // Collider
        Vector cTopLeft = new Vector(WIDTH_OFF/2, HEIGHT_OFF);
        Vector cDimensions = new Vector(WIDTH -WIDTH_OFF, HEIGHT -HEIGHT_OFF);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, cTopLeft, cDimensions)));

        // Layer
        addComponent(new RenderComponent(this, 999999999));

        // Physics
        addComponent(new PhysicsComponent(this, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));

        // Sensors
        Vector sTopLeft = new Vector(WIDTH_OFF/2 + ANTI_WALL_RUN, HEIGHT -JUMP_SENSOR_HEIGHT);
        Vector sDimensions = new Vector(WIDTH -WIDTH_OFF - ANTI_WALL_RUN*2, JUMP_SENSOR_HEIGHT + JUMP_SENSOR_EXTRA);
        AABBCollider ab = new AABBCollider(AABBCollider.MIN_DIM, sTopLeft, sDimensions);
        Sensor bottomSensor = new Sensor(ab);
        cDimensions.y = cDimensions.y - JUMP_SENSOR_HEIGHT;
        ab = new AABBCollider(AABBCollider.MIN_DIM, cTopLeft, cDimensions);
        Sensor enemySensor = new Sensor(ab);
        addComponent(new SensorComponent(this, new Sensor[]{bottomSensor, enemySensor}));
        addComponent(new SensorHandlerComponent(this));

        // Scripts
        PlayerScript pc = new PlayerScript(playerSprite, bottomSensor, enemySensor);
        CameraScript cs = new CameraScript(this, new Vector(WIDTH /2, HEIGHT /2));
        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(pc);
        scriptComponent.add(cs);
        addComponent(scriptComponent);
    }

    /**
     * The PlayerSprite handles the current sprite displayed for the player.
     * This changes based upon the player's current state.
     * @author Will Pearson
     * @author David Hack
     */
    public class PlayerSprite extends AnimatedSprite {

        /**
         * Constructor does not take any resource ID upon creation as you need
         * to define the map resource map structure that the sprite will use. This
         * is done with the addResource() method.
         *
         * @param transform  The animated sprite's relative position.
         * @param dimensions The width and height of the animated sprite.
         * @param frameTick  The time taken before the image should switch.
         */
        public PlayerSprite(Vector transform, Vector dimensions, int frameTick) {
            super(transform, dimensions, frameTick);
            String state = WALK_RIGHT;
            String[] resources = new String[] {
                    "player-walk2-right",
                    "player-walk-right"
            };
            addResources(state, Arrays.asList(resources));

            state = IDLE_RIGHT;
            resources = new String[] {
                    "player-right"
            };
            addResources(state, Arrays.asList(resources));

            state = JUMP_RIGHT;
            resources = new String[] {
                    "player-jump-right"
            };
            addResources(state, Arrays.asList(resources));

            state = DUCK_RIGHT;
            resources = new String[] {
                    "player-duck-right"
            };
            addResources(state, Arrays.asList(resources));

            state = SHOT_RIGHT;
            resources = new String[]{
                    "player-walk-right-bow"
            };
            addResources(state, Arrays.asList(resources));

            state = WALK_LEFT;
            resources = new String[] {
                    "player-walk2-left",
                    "player-walk-left"
            };
            addResources(state, Arrays.asList(resources));

            state = IDLE_LEFT;
            resources = new String[] {
                    "player-left"
            };
            addResources(state, Arrays.asList(resources));

            state = JUMP_LEFT;
            resources = new String[] {
                    "player-jump-left"
            };
            addResources(state, Arrays.asList(resources));

            state = DUCK_LEFT;
            resources = new String[] {
                    "player-duck-left"
            };
            addResources(state, Arrays.asList(resources));

            state = SHOT_LEFT;
            resources = new String[]{
                    "player-walk-left-bow"
            };
            addResources(state, Arrays.asList(resources));

            setState(IDLE_RIGHT);
        }
    }

    public static int getCoinCount(){
        return (int)COLLECTIBLE_ENTITIES.stream().filter(e -> e instanceof CoinEntity).count();
    }

    public static void spendCoins(int amount){

    }
}


