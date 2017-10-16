package ivara.entities.enemies;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import ivara.entities.PlayerEntity;
import ivara.entities.scripts.PatrolScript;
import ivara.entities.scripts.ShootScript;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;
import java.util.Optional;

/**
 * The BeeEntity constantly shoots BulletEntities at a target GameEntity.
 * The BeeEntity can be given a deviance of which it patrols back and forth between.
 * @author David Hack
 */
public class BeeEntity extends GameEntity implements Enemy{

    // Constants
    private final static float WIDTH = 0.8f;
    private final static float HEIGHT = 0.8f;
    private final static int ANIMATION_RATE = 150;

    /**
     * Construct a BeeEntity that deviates from it's initial position.
     * @param transform The initial position of the BeeEntity.
     * @param target The target entity to shoot at.
     * @param deviance A vector representing how far (x and y) the Bee deviates from the home position.
     */
    public BeeEntity(Vector transform, GameEntity target, Vector deviance){
        super(transform);
        if(deviance.equals(new Vector(0,0))) setup(transform, target, Optional.empty());
        else setup(transform,target,Optional.of(deviance));
    }

    /**
     * Construct a BeeEntity that simply shoots at a target.
     * @param transform The initial position of the BeeEntity.
     * @param target An Entity to shoot at.
     */
    public BeeEntity(Vector transform, GameEntity target){
        super(transform);
        setup(transform,target,Optional.empty());
    }

    /**
     * Does the relevant setup for the Entity.
     * @param transform The position of the entity.
     * @param target The target.
     * @param deviance An optional Vector that may have the deviance from the home location.
     */
    private void setup(Vector transform, GameEntity target, Optional<Vector> deviance){
        Vector dimension = new Vector(WIDTH, HEIGHT);

        // Velocity
        addComponent(new VelocityComponent(this));

        // Layer
        addComponent(new RenderComponent(this, 1000));

        // Collider
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        // Script
        ScriptComponent sComp = new ScriptComponent(this, new ShootScript(this, target, new Vector(PlayerEntity.WIDTH/2f, PlayerEntity.HEIGHT/2f))); // Always add shoot script
        if(deviance.isPresent())sComp.add(new PatrolScript(this, deviance.get(), 1f)); // If there is deviance, add patrol script.
        addComponent(sComp);

        // Sprite
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new BeeSprite(dimension, ANIMATION_RATE));
        addComponent(sc);
    }

    /**
     * This class handles the Sprites used by the BeeEntity.
     */
    private class BeeSprite extends AnimatedSprite {

        /**
         * Constructs a BeeSprite to be used by the BeeEntity.
         * @param dimensions The dimensions of the sprite.
         * @param frameTick How long before updating the sprite displayed.
         */
        private BeeSprite(Vector dimensions, int frameTick){
            super(new Vector(0,0), dimensions, frameTick);
            String state = "normal";
            String[] resources = new String[] {
                    "bee",
                    "bee2"
            };
            addResources(state, Arrays.asList(resources));
            setState("normal");
        }
    }
}
