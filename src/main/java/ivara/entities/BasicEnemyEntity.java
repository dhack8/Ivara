package ivara.entities;

import core.components.*;
import core.entity.GameEntity;
import ivara.scripts.Gravity;
import ivara.scripts.PlayerController;
import maths.Vector;
import physics.AABBCollider;
import physics.PhysicProperties;

/**
 * This is a move left/right enemy entity
 * @author Alex Mitchell
 * @author David Hack
 */
public class BasicEnemyEntity extends GameEntity {
    public BasicEnemyEntity(Vector transform) {
        super(transform);
    } // Todo make a super enemy entity

    /*
    private final float sensorWidth = 0.01f;
    private final float velocity = -1;
    private final float width = 0.75f;
    private final float height = 0.75f;

    VelocityComponent v;

    public BasicEnemyEntity(float x, float y){
        super(new Vector(x, y));

        v = new VelocityComponent(this);
        v.getVelocity().set(velocity, 0);
        addComponent(v);
        addComponent(new SpriteComponent(this, "slime", 0.75f, 0.75f)); //Todo change the PSprite component
        //addComponent(new PlayerController(this));
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.TOPLEFT, new Vector(0f, 0f), new Vector(width, height)))); //Todo Change the Collider component
        addComponent(new LayerComponent(this, 999));
        addComponent(new PhysicsComponent(this, 0, PhysicProperties.Type.DYNAMIC));

        //Bumpers to bounce on blocks
        addBlockBumper(new Vector(-sensorWidth, 0f), new Vector(sensorWidth, height - 0.01f));
        addBlockBumper(new Vector(width, 0f), new Vector(sensorWidth, height - 0.01f));

        //Bumpers for the edge


        addComponent( // for the bottom
                new SensorComponent(
                        this,
                        new AABBCollider(AABBCollider.TOPLEFT, new Vector(0f, height-sensorWidth), new Vector(width, sensorWidth)),
                        (entity) -> {v.getVelocity().y = 0;}
                )
        );
    }

    private void addBlockBumper(Vector topLeft, Vector dimensions){
        addComponent(
                new SensorComponent(
                        this,
                        new AABBCollider(
                                AABBCollider.TOPLEFT,
                                new Vector(topLeft.x, topLeft.y),
                                new Vector(dimensions.x, dimensions.y)
                        ),
                        (entity) -> {
                            v.getVelocity().mult(-1f);

                            float move = (v.getVelocity().x/v.getVelocity().x)*-1;
                            translate(sensorWidth*move , 0);
                        }
                )
        );
    }

    private void addEdgeBumper(Vector topLeft, Vector dimensions){
        addComponent(
                new SensorComponent(
                        this,
                        new AABBCollider(
                                AABBCollider.TOPLEFT,
                                new Vector(topLeft.x, topLeft.y),
                                new Vector(dimensions.x, dimensions.y)
                        ),
                        (entity) -> {
                            v.getVelocity().mult(-1f);

                            float move = (v.getVelocity().x/v.getVelocity().x)*-1;
                            translate(sensorWidth*move , 0);
                        }
                )
        );
    }
    */
}
