package ivara.entities.enemies;

import core.components.ColliderComponent;
import core.components.RenderComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;

/**
 * Created by David Hack Local on 10-Oct-17.
 */
public class BarnacleEntity extends GameEntity implements ImortalEnemy{

    private final static float WIDTH = 0.5f;
    private final static float HEIGHT = 0.7f;

    private final static int ANIMATION_RATE = 400;

    public BarnacleEntity(Vector transform){
        super(transform);
        Vector dimension = new Vector(WIDTH, HEIGHT);

        //Layer---
        addComponent(new RenderComponent(this, 1100));

        //Collider--
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        //Sprite---
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new BarnacleSprite(dimension, ANIMATION_RATE));
        addComponent(sc);
    }

    private class BarnacleSprite extends AnimatedSprite {
        private BarnacleSprite(Vector dimensions, int frameTick){
            super(new Vector(0,0), dimensions, frameTick);

            String state = "normal";
            String[] resources = new String[] {
                    "barnacle",
                    "barnacle2"
            };
            addResources(state, Arrays.asList(resources));

            setState("normal");
        }
    }
}

