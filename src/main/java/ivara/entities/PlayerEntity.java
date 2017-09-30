package ivara.entities;

import core.SensorListener;
import core.components.*;
import core.entity.GameEntity;
import core.struct.Camera;
import core.struct.Sensor;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;
import ivara.scripts.Gravity;
import ivara.scripts.PlayerController;

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

        VelocityComponent v = new VelocityComponent(this);
        addComponent(v);
        addComponent(new SpriteComponent(this, "player", 1, 1.5f)); //Todo change the PSprite component
       // addComponent(new PlayerController());

        PlayerController pc = new PlayerController();
        addComponent(new ScriptComponent(this, pc));
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


