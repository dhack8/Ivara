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
        addComponent(new PSpriteComponent(this, "player", 1, 1.5f)); //Todo change the PSprite component
        addComponent(new PlayerController(this));
        addComponent(new Gravity(this));
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.TOPLEFT, new Vector(0.2f, 0.3f), new Vector(0.6f, 1.2f)))); //Todo Change the Collider component
        addComponent(new LayerComponent(this, 999));
        addComponent(new PhysicsComponent(this, 1, PhysicProperties.Type.DYNAMIC));

        addComponent(
                new SensorComponent(
                        this,
                        new AABBCollider(
                                AABBCollider.TOPLEFT,
                                new Vector(0, 1.4f),
                                new Vector(1, 0.1f)
                        ),
                        (entity) -> {
                            System.out.println(entity);
                            canJump = true;
                            v.getVelocity().set(0, 0);

                            if (entity instanceof MovingBlockEntity) {
                                v.getVelocity().set(entity.getComponents(VelocityComponent.class).stream().findAny().get().getVelocity());
                            }
                        }
                )
        );
        addComponent(new BasicCameraComponent(this, 19));

    }
}


