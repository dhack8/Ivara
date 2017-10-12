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
 * This class handles the creation of a basic enemy entity
 * @author Alex Mitchell
 */
abstract public class BasicEnemyEntity extends GameEntity{
    //Initial setup variables -- change later?
    private static final float SPEED = 0.7f;
    private static final boolean START_RIGHT = true;
    private float sensorPadding = 0.1f;

    //Todo working on sensors for moving onto a non-existent platform
    private float groundSensorHeight= 0f;

    public BasicEnemyEntity(Vector transform, Vector dimensions){
        super(transform);

        //Entity dimensions
        float width = dimensions.x;
        float height = dimensions.y;

        //Side sensor dimensions
        float sensorWidth = 0f;
        float sideSensorHeight = height - 2* sensorPadding;

        //Bottom sensor dimensions
        float bottomSensorWidth = width - 2* sensorPadding;
        float bottomSensorHeight = 0f;

        //Velocity---
        VelocityComponent v = new VelocityComponent(this);
        v.set(new Vector((START_RIGHT? SPEED: -SPEED), 0f));
        addComponent(v);

        //Collider---
        Vector cTopLeft = new Vector(0f, 0f);
        Vector cDimensions = new Vector(width, height);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, cTopLeft, cDimensions)));

        //Layer---
        addComponent(new RenderComponent(this, 999));

        //Physics--- Todo: If there is a physics component, a sensor must be used to counter the increasing Y velocity
        //addComponent(new PhysicsComponent(this, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));

        //Sensors---
        //Side sensors
        Vector sTopLeft = new Vector(-sensorWidth, sensorPadding); // small indent so that the ground or blocks above arent detected
        Vector sTopRight = new Vector(width, sensorPadding);
        Vector sDimensions = new Vector(sensorWidth, sideSensorHeight);
        AABBCollider left = new AABBCollider(AABBCollider.MIN_DIM, sTopLeft, sDimensions);
        AABBCollider right = new AABBCollider(AABBCollider.MIN_DIM, sTopRight, sDimensions);
        Sensor leftSensor = new Sensor(left);
        Sensor rightSensor = new Sensor(right);

        //Bottom sensor
        Vector bottomLeft = new Vector(sensorPadding , height);
        Vector bottomDimensions = new Vector(bottomSensorWidth, bottomSensorHeight);
        AABBCollider bottom = new AABBCollider(AABBCollider.MIN_DIM, bottomLeft, bottomDimensions);
        Sensor groundSensor = new Sensor(bottom);

        //Edge detector sensors
        Vector bLeft = new Vector(-sensorWidth, height);
        Vector bRight = new Vector(width, height);
        Vector bDimensions = new Vector(sensorWidth, groundSensorHeight);

        AABBCollider bLCol = new AABBCollider(AABBCollider.MIN_DIM, bLeft, bDimensions);
        Sensor bLSensor = new Sensor(bLCol);
        AABBCollider bRCol = new AABBCollider(AABBCollider.MIN_DIM, bRight, bDimensions);
        Sensor bRSensor = new Sensor(bRCol);

        //Add sensors to sensor component
        addComponent(new SensorComponent(this, new Sensor[]{leftSensor, rightSensor, groundSensor, bLSensor, bRSensor}));

        //Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        //Scripts---
        //BasicEnemyScript s = new BasicEnemyScript(this, leftSensor, rightSensor, groundSensor);
        BasicEnemyScript s = new BasicEnemyScript(this, leftSensor, rightSensor, groundSensor, bLSensor, bRSensor);
        addComponent(new ScriptComponent(this, s));
    }

    public void setSprite(Sprite s){
        //Sprites---
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(s);
        addComponent(sc);
    }

    public void setSprite(AnimatedSprite s){
        //Sprites---
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(s);
        addComponent(sc);
    }
}
