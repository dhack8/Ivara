package ivara.entities.sprites;

import core.components.*;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import core.struct.Sensor;
import ivara.entities.Enemy;
import ivara.entities.scripts.BasicMoveScript;
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

    public GhostEntity(Vector transform, Vector target, float time){
        super(transform);
        Vector dimension = new Vector(WIDTH, HEIGHT);

        //Velocity---
        addComponent(new VelocityComponent(this));

        //Layer---
        addComponent(new LayerComponent(this, 1000));

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
