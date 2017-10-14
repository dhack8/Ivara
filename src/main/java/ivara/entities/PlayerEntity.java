package ivara.entities;

import core.components.*;
import core.entity.GameEntity;
import core.struct.Sensor;
import ivara.entities.sprites.PlayerSprite;
import ivara.entities.scripts.CameraScript;
import ivara.entities.scripts.PlayerScript;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;

/**
 * This class handles the entity of the player within the game
 *
 * @author Alex Mitchell
 * @author David Hack
 */
public class PlayerEntity extends GameEntity {

    //Dimensions
    public static final float WIDTH = 1f;
    public static final float HEIGHT = 1.5f;

    private float widthOff = 0.4f;
    private float heightOff = 0.3f;

    private float jumpSensorHeight = 0.15f;

    public int coinsCollected = 0;

    /**
     * Creates a PlayerEntity at a given position
     *
     * @param x The x position
     * @param y The y position
     */
    public PlayerEntity(float x, float y) {
        super(new Vector(x, y));

        //Velocity---
        VelocityComponent v = new VelocityComponent(this);
        addComponent(v);

        //Sprites---
        SpriteComponent sc = new SpriteComponent(this);
        //sc.add(new ResourceID("player"), new Vector(WIDTH, HEIGHT));
        PlayerSprite playerSprite = new PlayerSprite(new Vector(0,0), new Vector(WIDTH, HEIGHT), 160);
        sc.add(playerSprite);
        addComponent(sc);


        //Input---
        addComponent(new InputHandlerComponent(this));

        //Collider---
        Vector cTopLeft = new Vector(widthOff/2, heightOff);
        Vector cDimensions = new Vector(WIDTH -widthOff, HEIGHT -heightOff);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, cTopLeft, cDimensions))); //Todo Change the Collider component

        //Layer---
        addComponent(new RenderComponent(this, 999));

        //Physics---
        addComponent(new PhysicsComponent(this, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));

        //Sensors---
        //AABB for the sensor
        //FOR WALL RUNNING THIS SENSOR NEEDS TO BE THE SAME WIDTH AS THE COLLIDER, BIT SMALLER FOR NO WALL RUNNING
        //Vector sTopLeft = new Vector(widthOff/2, HEIGHT -jumpSensorHeight);
        //Vector sDimensions = new Vector(WIDTH -widthOff, jumpSensorHeight);
        Vector sTopLeft = new Vector(widthOff/2 + 0.05f, HEIGHT -jumpSensorHeight);
        Vector sDimensions = new Vector(WIDTH -widthOff - 0.1f, jumpSensorHeight);
        AABBCollider ab = new AABBCollider(AABBCollider.MIN_DIM, sTopLeft, sDimensions);
        Sensor bottomSensor = new Sensor(ab);

        //This sensor goes around whole player except the jump sensor
        cDimensions.y = cDimensions.y - jumpSensorHeight;
        ab = new AABBCollider(AABBCollider.MIN_DIM, cTopLeft, cDimensions);
        Sensor enemySensor = new Sensor(ab);

        addComponent(new SensorComponent(this, new Sensor[]{bottomSensor, enemySensor}));

        //Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        //Scripts---
        PlayerScript pc = new PlayerScript(playerSprite, bottomSensor, enemySensor);
        CameraScript cs = new CameraScript(this, new Vector(WIDTH /2, HEIGHT /2));

        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(pc);
        scriptComponent.add(cs);

        addComponent(scriptComponent);
    }
}


