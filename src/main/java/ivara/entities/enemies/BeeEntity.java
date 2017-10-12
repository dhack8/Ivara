package ivara.entities.enemies;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import ivara.entities.PlayerEntity;
import ivara.entities.scripts.PatrolScript;
import ivara.entities.scripts.ShootScript;
import ivara.entities.scripts.ShootScript2;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by David Hack Local on 10-Oct-17.
 */
public class BeeEntity extends GameEntity implements Enemy{

    private final static float WIDTH = 0.8f;
    private final static float HEIGHT = 0.8f;

    private final static int ANIMATION_RATE = 150;

    public BeeEntity(Vector transform, GameEntity target, Vector deviance){
        super(transform);
        setup(transform,target,Optional.of(deviance));
    }

    public BeeEntity(Vector transform, GameEntity target){
        super(transform);
        setup(transform,target,Optional.empty());
    }

    private void setup(Vector transform, GameEntity target, Optional<Vector> deviance){
        Vector dimension = new Vector(WIDTH, HEIGHT);

        //Velocity---
        addComponent(new VelocityComponent(this));

        //Layer---
        addComponent(new RenderComponent(this, 1000));

        //Collider--
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        //Script---
        ScriptComponent sComp = new ScriptComponent(this, new ShootScript2(this, target, new Vector(PlayerEntity.WIDTH/2f, PlayerEntity.HEIGHT/2f)));
        if(deviance.isPresent())sComp.add(new PatrolScript(this, deviance.get(), 1f));
        addComponent(sComp);

        //Sprite---
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new BeeSprite(dimension, ANIMATION_RATE));
        addComponent(sc);
    }

    private class BeeSprite extends AnimatedSprite {
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