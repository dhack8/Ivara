package ivara.entities;

import core.components.*;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sensor;
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
        sc.add(new ResourceID("player"), new Vector(1f, 1.5f));
        addComponent(sc);

        //Scripts---
        PlayerScript pc = new PlayerScript(this);
        CameraScript cs = new CameraScript(this, new Vector(0.5f, 0.75f));

        ScriptComponent scriptComponent = new ScriptComponent(this);
        scriptComponent.add(pc);
        scriptComponent.add(cs);

        addComponent(new ScriptComponent(this));

        //Input---
        addComponent(new InputComponent(this));

        //Collider---
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, new Vector(0.2f, 0.3f), new Vector(0.6f, 1.2f)))); //Todo Change the Collider component

        //Layer---
        addComponent(new LayerComponent(this, 999));

        //Physics---
        addComponent(new PhysicsComponent(this, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));

        //Sensors---
        //AABB for the sensor
        AABBCollider ab = new AABBCollider(AABBCollider.MIN_DIM, new Vector(0.199f, 1.4f), new Vector(0.611f, 0.1f));
        addComponent(new SensorComponent(this, new Sensor(ab, pc)));
    }
}


