package ivara.entities;

import core.Script;
import core.SensorListener;
import core.components.*;
import core.entity.GameEntity;
import core.struct.Camera;
import core.struct.ResourceID;
import core.struct.Sensor;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;
import ivara.scripts.Gravity;
import ivara.scripts.PlayerController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class handles the entity of the player within the game
 *
 * @author Alex Mitchell
 */
public class PlayerEntity extends GameEntity {

    //Vector previous = new Vector(0,0);

    public boolean canJump = false;
    /**
     * Creates a PlayerEntity at a given position
     *
     * @param x The x position
     * @param y The y position
     */
    public PlayerEntity(float x, float y) {
        super(new Vector(x, y));

        //Velocity
        VelocityComponent v = new VelocityComponent(this);
        addComponent(v);

        //Sprites
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new ResourceID("player"), new Vector(1f, 1.5f));
        addComponent(sc);

        //Scripts
        Set<Script> scripts = new HashSet<>(); // added multiple scripts
        PlayerController pc = new PlayerController(this);
        scripts.add(pc);

        addComponent(new ScriptComponent(this, scripts));
        //addComponent(new Gravity(this));
        //addComponent(new Gravity());

        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.TOPLEFT, new Vector(0.2f, 0.3f), new Vector(0.6f, 1.2f)))); //Todo Change the Collider component
        addComponent(new LayerComponent(this, 999));
        //addComponent(new PhysicsComponent(this, 1, PhysicProperties.Type.DYNAMIC));
        addComponent(new PhysicsComponent(this, new PhysicProperties(1, PhysicProperties.Type.DYNAMIC)));

        addComponent(
                new SensorComponent(
                        this,
                        new AABBCollider(
                                AABBCollider.TOPLEFT,
                                new Vector(0.199f, 1.4f),
                                new Vector(0.611f, 0.1f)
                        ),pc));
        //addComponent(new CameraComponent(this, 19));
        //addComponent(new CameraComponent(this, new Camera()));

    }
}


