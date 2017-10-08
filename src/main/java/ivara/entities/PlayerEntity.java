package ivara.entities;

import core.components.*;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sensor;
import ivara.entities.sprites.PlayerSprite;
import ivara.scripts.CameraScript;
import ivara.scripts.PlayerScript;
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

    //Restricts jumping
    public boolean canJump = false;

    //Dimensions
    private float width = 1f;
    private float height = 1.5f;

    private float widthOff = 0.4f;
    private float heightOff = 0.3f;

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
        //sc.add(new ResourceID("player"), new Vector(width, height));
        PlayerSprite playerSprite = new PlayerSprite(new Vector(0,0), new Vector(width, height), 160);
        sc.add(playerSprite);
        addComponent(sc);


        //Input---
        addComponent(new InputHandlerComponent(this));

        //Collider---
        Vector cTopLeft = new Vector(widthOff/2, heightOff);
        Vector cDimensions = new Vector(width-widthOff, height-heightOff);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, cTopLeft, cDimensions))); //Todo Change the Collider component

        //Layer---
        addComponent(new LayerComponent(this, 999));

        //Physics---
        addComponent(new PhysicsComponent(this, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));

        //Sensors---
        //AABB for the sensor
        //FOR WALL RUNNING THIS SENSOR NEEDS TO BE THE SAME WIDTH AS THE COLLIDER, BIT SMALLER FOR NO WALL RUNNING
        Vector sTopLeft = new Vector(widthOff/2, height-0.1f);
        Vector sDimensions = new Vector(width-widthOff, 0.1f);
        AABBCollider ab = new AABBCollider(AABBCollider.MIN_DIM, sTopLeft, sDimensions);
        Sensor bottomSensor = new Sensor(ab);
        addComponent(new SensorComponent(this, bottomSensor));

        //Enable Listening for Sensor Events
        addComponent(new SensorHandlerComponent(this));

        //Scripts---
        PlayerScript pc = new PlayerScript(this, playerSprite, bottomSensor);
        CameraScript cs = new CameraScript(this, new Vector(width/2, height/2));

        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(pc);
        scriptComponent.add(cs);

        addComponent(scriptComponent);
    }
}


