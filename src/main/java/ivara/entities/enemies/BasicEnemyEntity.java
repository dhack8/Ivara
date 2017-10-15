package ivara.entities.enemies;

import core.components.*;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import core.struct.Sensor;
import core.struct.Sprite;
import ivara.entities.scripts.BasicEnemyScript;
import maths.Vector;
import physics.AABBCollider;

/**
 * This class handles the construction of a basic enemy entity.
 * @author Alex Mitchell
 */
abstract public class BasicEnemyEntity extends GameEntity{
    
    // Constants
    private static final float SPEED = 0.7f; // Movement speed
    private static final boolean START_RIGHT = true; // Initial movement direction
    private static final float SENSOR_PADDING = 0.1f; // Padding for the sensor
    private static final float GROUND_SENSOR_HEIGHT= 0f; // Height of the ground sensor

    /**
     * Constructs a BasicEnemyEntity that moves until it collides or cannot detect a block.
     * At this point, the entity changes direction.
     * @param transform The position if the entity.
     * @param dimensions The dimensions of the entity.
     */
    public BasicEnemyEntity(Vector transform, Vector dimensions){
        super(transform);

        // Entity dimensions
        float width = dimensions.x;
        float height = dimensions.y;

        // Side sensor dimensions
        float sensorWidth = 0f;
        float sideSensorHeight = height - 2* SENSOR_PADDING;

        // Bottom sensor dimensions
        float bottomSensorWidth = width - 2* SENSOR_PADDING;
        float bottomSensorHeight = 0f;

        // Entity Velocity
        VelocityComponent v = new VelocityComponent(this);
        v.set(new Vector((START_RIGHT? SPEED: -SPEED), 0f));
        addComponent(v);

        // Entity Collider
        Vector cTopLeft = new Vector(0f, 0f);
        Vector cDimensions = new Vector(width, height);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, cTopLeft, cDimensions)));

        // Entity Layer component
        addComponent(new RenderComponent(this, 999));

        // Sensors
        // Side sensors
        Vector sTopLeft = new Vector(-sensorWidth, SENSOR_PADDING); // Small indent so that the ground or blocks above aren't detected
        Vector sTopRight = new Vector(width, SENSOR_PADDING);
        Vector sDimensions = new Vector(sensorWidth, sideSensorHeight);
        AABBCollider left = new AABBCollider(AABBCollider.MIN_DIM, sTopLeft, sDimensions);
        AABBCollider right = new AABBCollider(AABBCollider.MIN_DIM, sTopRight, sDimensions);
        Sensor leftSensor = new Sensor(left);
        Sensor rightSensor = new Sensor(right);

        // Bottom sensor
        Vector bottomLeft = new Vector(SENSOR_PADDING , height);
        Vector bottomDimensions = new Vector(bottomSensorWidth, bottomSensorHeight);
        AABBCollider bottom = new AABBCollider(AABBCollider.MIN_DIM, bottomLeft, bottomDimensions);
        Sensor groundSensor = new Sensor(bottom);

        // Edge detector sensors
        Vector bLeft = new Vector(-sensorWidth, height);
        Vector bRight = new Vector(width, height);
        Vector bDimensions = new Vector(sensorWidth, GROUND_SENSOR_HEIGHT);
        AABBCollider bLCol = new AABBCollider(AABBCollider.MIN_DIM, bLeft, bDimensions);
        Sensor bLSensor = new Sensor(bLCol);
        AABBCollider bRCol = new AABBCollider(AABBCollider.MIN_DIM, bRight, bDimensions);
        Sensor bRSensor = new Sensor(bRCol);

        // Add sensors to sensor component
        addComponent(new SensorComponent(this, new Sensor[]{leftSensor, rightSensor, groundSensor, bLSensor, bRSensor}));

        // Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        // Scripts
        BasicEnemyScript s = new BasicEnemyScript(this, leftSensor, rightSensor, groundSensor, bLSensor, bRSensor);
        addComponent(new ScriptComponent(this, s));
    }

    /**
     * Sets the entity to have a specified Sprite.
     * @param s The sprite to set.
     */
    public void setSprite(Sprite s){
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(s);
        addComponent(sc);
    }

    /**
     * Sets the entity to have a specified Animated Sprite.
     * @param s The sprite to set.
     */
    public void setSprite(AnimatedSprite s){
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(s);
        addComponent(sc);
    }
}
