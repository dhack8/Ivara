package ivara.entities;

import core.components.*;
import core.entity.Entity;
import ivara.scripts.Gravity;
import ivara.scripts.PlayerController;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;

/**
 * This is a move left/right enemy entity
 * @author Alex Mitchell
 */
public class BasicEnemyEntity extends Entity{ // Todo make a super enemy entity
    public BasicEnemyEntity(float x, float y){
        super(new Vector(x, y));
/**
        // Todo consider velocity component
        VelocityComponent v = new VelocityComponent(this);
        addComponent(v);

        //Todo add a script

        addComponent(new PSpriteComponent(this, "slime", 0.75f, 0.75f));
        addComponent(new Gravity(this));
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.TOPLEFT, new Vector(0f,0f), new Vector(0.75f, 0.75f))));
        addComponent(new LayerComponent(this, 999));

        addComponent(new PhysicsComponent(this, 1, PhysicProperties.Type.DYNAMIC));
**/

        VelocityComponent v = new VelocityComponent(this);
        v.getVelocity().set(-1f, 0);
        addComponent(v);
        addComponent(new PSpriteComponent(this, "slime", 0.75f, 0.75f)); //Todo change the PSprite component
        //addComponent(new PlayerController(this));
        addComponent(new Gravity(this));
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.TOPLEFT, new Vector(0f, 0f), new Vector(0.75f, 0.75f)))); //Todo Change the Collider component
        addComponent(new LayerComponent(this, 999));
        addComponent(new PhysicsComponent(this, 0, PhysicProperties.Type.DYNAMIC));


        addComponent(
                new SensorComponent(
                        this,
                        new AABBCollider( // Todo set for the left block collision
                                AABBCollider.TOPLEFT,
                                new Vector(-0.01f, 0f),
                                new Vector(0.01f, 0.75f)
                        ),
                        (entity) -> {
                            v.getVelocity().mult(-1f);
                        }
                )
        );

        addComponent(
                new SensorComponent(
                        this,
                        new AABBCollider( // Todo set for the right block collision
                                AABBCollider.TOPLEFT,
                                new Vector(0.75f, 0f),
                                new Vector(0.01f, 0.75f)
                        ),
                        (entity) -> {
                            v.getVelocity().mult(-1f);
                        }
                )
        );

        addComponent( // for the bottom
                new SensorComponent(
                        this,
                        new AABBCollider(AABBCollider.TOPLEFT, new Vector(0f, 0.7f), new Vector(0.75f, 0.05f)),
                        (entity) -> {v.getVelocity().y = 0;}
                )
        );
    }


}
