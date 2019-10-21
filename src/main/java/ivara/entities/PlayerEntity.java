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

import java.security.KeyException;
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

    private static int COINS = 0;
    private static int COINS_USED = 0;

    private static Map<String, Float> ITEM_FLAGS = getInitialItemFlags();
    private static SKIN ACTIVE_SKIN = SKIN.PABLO;

    private int arrowsFired = 0;
    private int arrowsFiredSinceCheckpoint = 0;


    public enum SKIN {
        PABLO("pablo"),
        STACY("stacy"),
        NIGEL("nigel"),
        SIMON("simon"),
        ZOMBIE("zombie"),
        SNOWMAN("snowman");

        private String value;

        SKIN(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

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
        BootsSprite bootsSprite = new BootsSprite(new Vector(0,0), new Vector(WIDTH, HEIGHT), 160);
        sc.add(playerSprite);
        sc.add(crossbowSprite);
        sc.add(bootsSprite);
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
        PlayerScript pc = new PlayerScript(playerSprite, crossbowSprite, bootsSprite, bottomSensor, enemySensor);
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

    public static SKIN getActiveSkin(){
        return ACTIVE_SKIN;
    }

    public static void setActiveSkin(SKIN skin){
        ACTIVE_SKIN = skin;
    }

    public class PlayerSprite extends AnimatedSprite {
        public PlayerSprite(Vector transform, Vector dimensions, int frameTick) {
            super(transform, dimensions, frameTick);
            addResources("player-walk-right", Arrays.asList(ACTIVE_SKIN + "-walk-right", ACTIVE_SKIN + "-walk2-right"));
            addResources("player-walk-left", Arrays.asList(ACTIVE_SKIN + "-walk-left", ACTIVE_SKIN + "-walk2-left"));
            addResources("player-idle-right", Arrays.asList(ACTIVE_SKIN + "-right"));
            addResources("player-idle-left", Arrays.asList(ACTIVE_SKIN + "-left"));
            addResources("player-jump-right", Arrays.asList(ACTIVE_SKIN + "-jump-right"));
            addResources("player-jump-left", Arrays.asList(ACTIVE_SKIN + "-jump-left"));
        }
    }

    public class CrossbowSprite extends AnimatedSprite {
        public CrossbowSprite(Vector transform, Vector dimensions, int frameTick) {
            super(transform, dimensions, frameTick);
            addResources("crossbow-hidden", Arrays.asList("transparent"));
            addResources("crossbow-walk-left", Arrays.asList("crossbow-walk-left", "crossbow-walk2-left"));
            addResources("crossbow-walk-right", Arrays.asList("crossbow-walk-right", "crossbow-walk2-right"));
            addResources("crossbow-idle-left", Arrays.asList("crossbow-left"));
            addResources("crossbow-idle-right", Arrays.asList("crossbow-right"));
            addResources("crossbow-jump-left", Arrays.asList("crossbow-jump-left"));
            addResources("crossbow-jump-right", Arrays.asList("crossbow-jump-right"));
            setState("crossbow-hidden");
        }
    }

    public class BootsSprite extends AnimatedSprite {
        public BootsSprite(Vector transform, Vector dimensions, int frameTick) {
            super(transform, dimensions, frameTick);
            addResources("boots-hidden", Arrays.asList("transparent"));
            addResources("boots-walk-left", Arrays.asList("boots-walk-left", "boots-walk2-left"));
            addResources("boots-walk-right", Arrays.asList("boots-walk-right", "boots-walk2-right"));
            addResources("boots-idle-left", Arrays.asList("boots-left"));
            addResources("boots-idle-right", Arrays.asList("boots-right"));
            addResources("boots-jump-left", Arrays.asList("boots-jump-left"));
            addResources("boots-jump-right", Arrays.asList("boots-jump-right"));
            setState("boots-hidden");
        }
    }

    public static int getCoinCount(){
        return COINS;
    }

    public static void bankCoins(int amount){
        COINS += amount;
    }

    public static void spendCoins(int amount){

    }

    private static Map<String, Float> getInitialItemFlags(){
        Map<String, Float> itemFlags = new HashMap<>();

        // Crossbow initial flags
        itemFlags.put("crossbow-pre-delay", 200f);
        itemFlags.put("crossbow-multishot", 0f);
        itemFlags.put("crossbow-post-delay", 1000f);
        itemFlags.put("crossbow-duration", 2000f) ;
        itemFlags.put("crossbow-moving", 1f);
        itemFlags.put("crossbow-arrow-speed", 15f);
        itemFlags.put("crossbow-collected", 1f);
        itemFlags.put("crossbow-quiver-size", 3f);

        // Jump boots initial flags
        itemFlags.put("boots-num-additional-jumps", 0f);
        itemFlags.put("boots-jump-boost", 0f);
        itemFlags.put("boots-successive-jump-power", 0.7f);
        itemFlags.put("boots-super-jump-power", 1.2f);
        itemFlags.put("boots-sprint", 1f);
        itemFlags.put("boots-sprint-multiplier", 1.5f);
        itemFlags.put("boots-collected", 0f);

        return itemFlags;
    }

    public static void setItemFlag(String flag, float value) {
        if(ITEM_FLAGS.containsKey(flag)) {
            float currentValue = ITEM_FLAGS.get(flag);
            if(value > currentValue) {
                ITEM_FLAGS.put(flag, value);
            }
        } else {
            throw new IllegalArgumentException("Flag does not exist");
        }
    }

    // Methods for accessing flag variables

    // Crossbow

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

    // Boots

    public static boolean hasBoots(){
        return ITEM_FLAGS.get("boots-collected").intValue() >= 1;
    }

    public static int getBootsAdditionalJumps(){
        return ITEM_FLAGS.get("boots-num-additional-jumps").intValue();
    }

    public static float getBootsAdditionalHeight(){
        return ITEM_FLAGS.get("boots-jump-boost");
    }

    public static float getBootsSuccessiveJumpPower(){
        return ITEM_FLAGS.get("boots-successive-jump-power");
    }

    public static float getBootsSuperJumpPower(){
        return ITEM_FLAGS.get("boots-super-jump-power");
    }

    public static boolean canSprint(){
        return ITEM_FLAGS.get("boots-sprint").intValue() >= 1;
    }

    public static float getSprintMultiplier(){
        return ITEM_FLAGS.get("boots-sprint-multiplier");
    }

}


