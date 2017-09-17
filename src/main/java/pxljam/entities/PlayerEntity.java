package pxljam.entities;

import core.components.*;
import core.entity.Entity;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;
import pxljam.scripts.Gravity;
import pxljam.scripts.PlayerController;

/**
 * This class handles the entity of the player within the game
 *
 * @author Alex Mitchell
 */
public class PlayerEntity extends Entity {

    //Vector previous = new Vector(0,0);

    /**
     * Creates a PlayerEntity at a given position
     *
     * @param x The x position
     * @param y The y position
     */
    public PlayerEntity(float x, float y) {
        super(new Vector(x, y));

        addComponent(new VelocityComponent(this));
        addComponent(new PSpriteComponent(this, "player", 1, 1.5f)); //Todo change the PSprite component
        addComponent(new PlayerController(this));
        addComponent(new Gravity(this));
        addComponent(new ColliderComponent(this, new AABBCollider(new Vector(0.5f, 0.75f), new Vector(0.5f, 0.75f)))); //Todo Change the Collider component
        addComponent(new LayerComponent(this, 999));
        addComponent(new PhysicsComponent(this, 1, PhysicProperties.Type.DYNAMIC));

        addComponent(
                new SensorComponent(
                        this,
                        new AABBCollider(
                                AABBCollider.TOPLEFT,
                                new Vector(0, 1.6f),
                                new Vector(1, 0.1f)
                        ),
                        (entity) -> System.out.println(entity)
                )
        );
        addComponent(new BasicCameraComponent(this, 19));
    }
}


