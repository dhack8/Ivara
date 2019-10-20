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

    public static Set<GameEntity> COLLECTIBLE_ENTITIES = new HashSet<>();
    public static Set<GameEntity> USED_COLLECTIBLE_ENTITIES = new HashSet<>();

    public static Map<String, Float> ITEM_FLAGS = getInitialItemFlags();

    private int arrowsFired = 0;
    private int arrowsFiredSinceCheckpoint = 0;

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
        CrossbowSprite crossbowSprite = new CrossbowSprite(new Vector(0,0), new Vector(WIDTH, HEIGHT), 160);
        sc.add(playerSprite);
        sc.add(crossbowSprite);
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
        PlayerScript pc = new PlayerScript(playerSprite, crossbowSprite, bottomSensor, enemySensor);
        CameraScript cs = new CameraScript(this, new Vector(WIDTH /2, HEIGHT /2));
        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(pc);
        scriptComponent.add(cs);
        addComponent(scriptComponent);
    }

    public int getArrowsFired(){
        return this.arrowsFired;
    }

    public void setArrowCheckpoint(){
        this.arrowsFiredSinceCheckpoint = 0;
    }

    public void fireArrow(){
        this.arrowsFired++;
        this.arrowsFiredSinceCheckpoint++;
    }

    public void resetArrowsToCheckpoint(){
        this.arrowsFired -= this.arrowsFiredSinceCheckpoint;
        this.arrowsFiredSinceCheckpoint = 0;
    }

    public void resetArrowsFired(){
        this.arrowsFired = this.arrowsFiredSinceCheckpoint = 0;
    }

    public class PlayerSprite extends AnimatedSprite {
        public PlayerSprite(Vector transform, Vector dimensions, int frameTick) {
            super(transform, dimensions, frameTick);
            addResources("player-walk-right", List.of("player-walk-right", "player-walk2-right"));
            addResources("player-walk-left", List.of("player-walk-left", "player-walk2-left"));
            addResources("player-idle-right", List.of("player-right"));
            addResources("player-idle-left", List.of("player-left"));
            addResources("player-jump-right", List.of("player-jump-right"));
            addResources("player-jump-left", List.of("player-jump-left"));
        }
    }

    public class CrossbowSprite extends AnimatedSprite {
        public CrossbowSprite(Vector transform, Vector dimensions, int frameTick) {
            super(transform, dimensions, frameTick);
            addResources("crossbow-hidden", List.of("transparent"));
            addResources("crossbow-walk-left", List.of("crossbow-walk-left", "crossbow-walk2-left"));
            addResources("crossbow-walk-right", List.of("crossbow-walk-right", "crossbow-walk2-right"));
            addResources("crossbow-idle-left", List.of("crossbow-left"));
            addResources("crossbow-idle-right", List.of("crossbow-right"));
            addResources("crossbow-jump-left", List.of("crossbow-jump-left"));
            addResources("crossbow-jump-right", List.of("crossbow-jump-right"));
            setState("crossbow-hidden");
        }
    }

    public static int getCoinCount(){
        return (int)COLLECTIBLE_ENTITIES.stream().filter(e -> e instanceof CoinEntity).count();
    }

    private static Map<String, Float> getInitialItemFlags(){
        Map<String, Float> itemFlags = new HashMap<>();

        // Crossbow initial flags
        itemFlags.put("crossbow-pre-delay", 200f);
        itemFlags.put("crossbow-multishot", 0f);
        itemFlags.put("crossbow-post-delay", 1000f);
        itemFlags.put("crossbow-duration", 2000f) ;
        itemFlags.put("crossbow-moving", 0f);
        itemFlags.put("crossbow-arrow-speed", 15f);
        itemFlags.put("crossbow-collected", 1f);
        itemFlags.put("crossbow-quiver-size", 3f);

        // Jump boots initial flags
        itemFlags.put("boots-num-additional-jumps", 0f);
        itemFlags.put("boots-jump-boost", 0f);
        itemFlags.put("boots-additional-jump-power", 0.7f);
        itemFlags.put("boots-sprint-multiplier", 1f);

        return itemFlags;
    }

    // Methods for accessing flag variables

    public static boolean hasCrossbow(){
        return ITEM_FLAGS.get("crossbow-collected").intValue() >= 1;
    }

    public static int getCrossbowPreShotDelay(){
        return ITEM_FLAGS.get("crossbow-pre-delay").intValue();
    }

    public static int getCrossbowPostShotDelay(){
        return ITEM_FLAGS.get("crossbow-post-delay").intValue();
    }

    public static float getCrossbowShotSpeed(){
        return ITEM_FLAGS.get("crossbow-arrow-speed");
    }

    public static int getCrossbowShotDuration(){
        return ITEM_FLAGS.get("crossbow-duration").intValue();
    }

    public static boolean hasMultishotCrossbow(){
        return ITEM_FLAGS.get("crossbow-multishot").intValue() >= 1;
    }

    public static boolean canShootWhileMoving(){
        return ITEM_FLAGS.get("crossbow-moving").intValue() >= 1;
    }

    public static int getCrossbowQuiverSize(){
        return ITEM_FLAGS.get("crossbow-quiver-size").intValue();
    }
}


