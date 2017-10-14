package ivara.entities.enemies;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import ivara.entities.enemies.Enemy;
import ivara.entities.scripts.BasicMoveScript;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;

/**
 * Ghost entity that roams around a certain area, moving through walls if needed, and damages the player if the player
 * comes in contact with the ghost.
 */
public class GhostEntity extends GameEntity implements Enemy{
    private static final float SPEED = 0.7f;

    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1.3f;

    private final static int ANIMATION_RATE = 600;

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
        sc.add(new GhostSprite(dimension, ANIMATION_RATE));
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
        addComponent(new RenderComponent(this, 999999));

        //Collider--
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        //Script--
        addComponent(new ScriptComponent(this, new GhostScript(target)));

        //Sprite---
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new GhostSprite(dimension, ANIMATION_RATE));
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

    /**
     * This script allows an entity to chase an enemy, and patrol when it's too far away from the home
     * Created by Alex Mitchell on 9/10/2017.
     * @author David Hack
     * @author Alex Mitchell
     */
    public class GhostScript implements Script {
            private GameEntity targetEntity;

            private final Vector homePos;

            private static final float DETECTION_RADIUS = 5f;
            private static final float HOME_RADIUS = 10f;

            private static final float SPEED = 1.2f;

            private static final float THRESHOLD = 1.2f; // could overshoot

            private static final float PATH_SIZE = 7f; // distance patrolled from home loaation

            private Vector p1; // the patrol positions
            private Vector p2;
            private Vector patrolTarget;

            private Vector desiredPos;

            /**
             * This script chases a target and patrols a "home" location when not chasing
             * @param targetEntity The entity to chase
             */
            public GhostScript(GameEntity targetEntity){
                this.targetEntity = targetEntity;

                homePos = new Vector(GhostEntity.this.getTransform());
                p1 = new Vector(homePos.x -PATH_SIZE/2, homePos.y); // temporarily, the patrol positions are +-2.5f on the x plane in relation to the home location
                p2 = new Vector(homePos.x +PATH_SIZE/2, homePos.y);

                desiredPos = patrolTarget = p2;
            }

            @Override
            public void update(int dt, GameEntity entity) {
                Vector targetPos = targetEntity.getTransform();
                Vector entityPos = entity.getTransform();

                if(targetPos.dist(entityPos) < DETECTION_RADIUS){ // if to chase detected
                    if(targetPos.dist(homePos) > HOME_RADIUS){ // if outside of home
                        Vector homeToChase = targetPos.sub(homePos).norm();
                        homeToChase.scaleBy(HOME_RADIUS);
                        desiredPos = homeToChase.add(homePos);
                    }else{ // if inside home
                        desiredPos = targetPos;
                    }
                }else{ // if the player is not within the detection radius
                    if(atPoint(entityPos, patrolTarget)) switchPatrolTarget();
                    desiredPos = patrolTarget;
                }

                Vector velocity = desiredPos.sub(entityPos).norm();
                velocity.scaleBy(SPEED);
                entity.get(VelocityComponent.class).get().set(velocity);
            }

            private void switchPatrolTarget(){
                patrolTarget = (patrolTarget.equals(p1))? p2:p1;
            }

            private boolean atPoint(Vector location, Vector point){
                return location.dist(point) <= THRESHOLD;
            }


    }
}
