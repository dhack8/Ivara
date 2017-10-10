package ivara.entities.enemies;

import core.components.*;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import ivara.entities.enemies.Enemy;
import ivara.entities.scripts.BasicMoveScript;
import ivara.entities.scripts.ChargeScript;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;

/**
 * Created by David Hack Local on 08-Oct-17.
 */
public class GhostEntity extends GameEntity implements Enemy{
    private static final float SPEED = 0.7f;

    private final static float WIDTH = 1.2f;
    private final static float HEIGHT = 1.7f;

    private final static int RATE = 600;

    /**
     * Constructs a ghost entity that floats between two points
     * @param transform The starting location
     * @param target The target location
     * @param time The time taken to reach the target location
     */
    public GhostEntity(Vector transform, Vector target, float time){
        super(transform);
        Vector dimension = new Vector(WIDTH, HEIGHT);

        //Velocity---
        addComponent(new VelocityComponent(this));

        //Layer---
        addComponent(new RenderComponent(this, 1000));

        //Collider--
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        //Script---
        addComponent(new ScriptComponent(this, new BasicMoveScript(this, target, time)));

        //Sprite---
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new GhostSprite(dimension, RATE));
        addComponent(sc);
    }

    /**
     * Constructs a ghost that chases the player in intervals.
     * The ghost resets on a collision
     * @param target The entity to target
     * @param transform The starting position
     */
    public GhostEntity(Vector transform, GameEntity target){
        super(transform);
        Vector dimension = new Vector(WIDTH, HEIGHT);

        //Velocity---
        addComponent(new VelocityComponent(this));

        //Layer---
        addComponent(new RenderComponent(this, 1000));

        //Collider--
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        //Script--
        addComponent(new ScriptComponent(this, new ChargeScript(this, target)));

        //Sprite---
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new GhostSprite(dimension, RATE));
        addComponent(sc);
    }



    private class GhostSprite extends AnimatedSprite {
        private GhostSprite(Vector dimensions, int frameTick){
            super(new Vector(0,0), dimensions, frameTick);

            String state = "normal";
            String[] resources = new String[] {
                    "ghost",
                    "ghost2"
            };
            addResources(state, Arrays.asList(resources));

            setState("normal");
        }
    }
}
