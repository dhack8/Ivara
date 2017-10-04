package ivara.entities.sprites;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sensor;
import ivara.scripts.BasicEnemyScript;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;

/**
 * This class handles the creation of a basic enemy entity
 * @author Alex Mitchell
 */
public class NewBasicEnemyEntity extends GameEntity{
    //Initial setup variables -- change later?
    private static final float SPEED = 1f;
    private static final boolean START_RIGHT = true;

    //Entity dimensions
    private float width = 1f;
    private float height = 1f;

    //Side sensor offsets
    private float sideSensorXOffset = 0f;
    private float sideSensorYOffset = 0.1f;
    //Side sensor dimensions
    private float sideSensorWidth = 0f;
    private float sideSensorHeight = height;

    //Bottom sensor offsets
    private float bottomSensorXOffset = 0.1f;
    private float bottomSensorYOffset = 0f;
    //Bottom sensor dimensions
    private float bottomSensorWidth = width;
    private float bottomSensorHeight = 0f;


    //Todo working on sensors for moving onto a non-existent platform

    private float groundSensorXOffset = 1f;
    private float groundSensorYOffset = 0f;
    private float groundSensorWidth = 1f;
    private float groundSensorHeight= 1f;

    public NewBasicEnemyEntity(Vector transform, String resourceID){
        super(transform);

        //Velocity---
        VelocityComponent v = new VelocityComponent(this);
        v.set(new Vector((START_RIGHT? SPEED: -SPEED), 0f));
        addComponent(v);

        //Sprites---
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new ResourceID(resourceID), new Vector(width, height));
        addComponent(sc);

        //Collider---
        Vector cTopLeft = new Vector(0f, 0f);
        Vector cDimensions = new Vector(width, height);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, cTopLeft, cDimensions)));

        //Layer---
        addComponent(new LayerComponent(this, 999));

        //Physics--- Todo: If there is a physics component, a sensor must be used to counter the increasing Y velocity
        //addComponent(new PhysicsComponent(this, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));

        //Sensors---
        //Side sensors
        Vector sTopLeft = new Vector(sideSensorXOffset, sideSensorYOffset); // small indent so that the ground or blocks above arent detected
        Vector sTopRight = new Vector(width - sideSensorXOffset, sideSensorYOffset);
        Vector sDimensions = new Vector(sideSensorWidth - (2*sideSensorXOffset), sideSensorHeight-(2*sideSensorYOffset));
        AABBCollider left = new AABBCollider(AABBCollider.MIN_DIM, sTopLeft, sDimensions);
        AABBCollider right = new AABBCollider(AABBCollider.MIN_DIM, sTopRight, sDimensions);
        Sensor leftSensor = new Sensor(left);
        Sensor rightSensor = new Sensor(right);
        //Bottom sensor
        Vector bottomLeft = new Vector(bottomSensorXOffset, height);
        Vector bottomDimensions = new Vector(bottomSensorWidth - (2*bottomSensorXOffset) , bottomSensorHeight- (2*bottomSensorYOffset));
        AABBCollider bottom = new AABBCollider(AABBCollider.MIN_DIM, bottomLeft, bottomDimensions);
        Sensor groundSensor = new Sensor(bottom);

        //Bottom sensors
        Vector bLeft = new Vector(-groundSensorXOffset, height);
        Vector bRight = new Vector(width, height);
        Vector bDimensions = new Vector(groundSensorWidth, groundSensorHeight);

        AABBCollider bLCol = new AABBCollider(AABBCollider.MIN_DIM, bLeft, bDimensions);
        Sensor bLSensor = new Sensor(bLCol);
        AABBCollider bRCol = new AABBCollider(AABBCollider.MIN_DIM, bRight, bDimensions);
        Sensor bRSensor = new Sensor(bRCol);

        //Add sensors to sensor component
        SensorComponent sComp = new SensorComponent(this, new Sensor[]{leftSensor, rightSensor, groundSensor, bLSensor, bRSensor});
        addComponent(sComp);
        //Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        //Scripts---
        //BasicEnemyScript s = new BasicEnemyScript(this, leftSensor, rightSensor, groundSensor);
        BasicEnemyScript s = new BasicEnemyScript(this, leftSensor, rightSensor, groundSensor, bLSensor, bRSensor);
        ScriptComponent scriptComp = new ScriptComponent(this, s);
        addComponent(scriptComp);
    }
}
