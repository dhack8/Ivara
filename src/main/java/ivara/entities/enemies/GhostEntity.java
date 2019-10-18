package ivara.entities.enemies;

import core.Script;
import core.components.*;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import ivara.entities.Removable;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;

/**
 * A Ghost entity roams around a certain area, moving through walls if needed, and kills the player if the player
 * comes in contact with the ghost.
 *
 * @author David Hack
 */
public class GhostEntity extends GameEntity implements Enemy, Removable {

    // Constants
    private final static float WIDTH = 1f;
    private final static float HEIGHT = 1.3f;
    private final static int ANIMATION_RATE = 600;

    /**
     * Constructs a ghost that chases the player in intervals.
     * The ghost resets on a collision
     *
     * @param target    The entity to target
     * @param transform The starting position
     */
    public GhostEntity(Vector transform, GameEntity target) {
        super(transform);
        Vector dimension = new Vector(WIDTH, HEIGHT);

        // Velocity
        addComponent(new VelocityComponent(this));

        // Layer
        addComponent(new RenderComponent(this, 999999));

        // Collider
        Vector topLeft = new Vector(0, 0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        // Script
        addComponent(new ScriptComponent(this, new GhostScript(target)));

        // Sprite
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new GhostSprite(dimension, ANIMATION_RATE));
        addComponent(sc);
    }

    /**
     * Constructs a Sprite that is used by the GhostEntity.
     *
     * @author David Hack
     */
    private class GhostSprite extends AnimatedSprite {
        private GhostSprite(Vector dimensions, int frameTick) {
            super(new Vector(0, 0), dimensions, frameTick);
            String state = "normal";
            String[] resources = new String[]{
                    "ghost",
                    "ghost2"
            };
            addResources(state, Arrays.asList(resources));
            setState("normal");
        }
    }

    /**
     * This script allows an entity to chase an enemy, and patrol when it's too far away from the home.     *
     * @author David Hack
     * @author Alex Mitchell
     */
    public class GhostScript implements Script {
        private GameEntity targetEntity; // Entity that the GhostEntity will chase if it detects it.
        private final Vector homePos; // The initial position of the entity.

        private static final float DETECTION_RADIUS = 5f; // The radius that the ghost can detect the player in.
        private static final float HOME_RADIUS = 10f; // The maximum distance the ghost can travel away from the home location.
        private static final float PATH_SIZE = 7f; // The distance between the two patrol positions.
        private static final float SPEED = 1.2f; // The speed of the ghost in meters per second.
        private static final float THRESHOLD = 1.2f; // A threshold used to determine if a vector is "equal to" another.

        private Vector p1; // The patrol positions
        private Vector p2;
        private Vector patrolTarget; // The current patrol position

        private Vector desiredPos; // The position the ghost is moving toward

        /**
         * This script chases a target and patrols a "home" location when not chasing.
         * @param targetEntity The entity to chase.
         */
        public GhostScript(GameEntity targetEntity) {
            this.targetEntity = targetEntity;
            homePos = new Vector(GhostEntity.this.getTransform());
            p1 = new Vector(homePos.x - PATH_SIZE / 2, homePos.y);
            p2 = new Vector(homePos.x + PATH_SIZE / 2, homePos.y);

            desiredPos = patrolTarget = p2;
        }

        @Override
        public void update(int dt, GameEntity entity) {
            Vector targetPos = targetEntity.getTransform();
            Vector entityPos = entity.getTransform();

            if (targetPos.dist(entityPos) < DETECTION_RADIUS) { // If the target is within the ghost's detection radius
                if (targetPos.dist(homePos) > HOME_RADIUS) { // If the target is outside of the ghost's home radius
                    Vector homeToChase = targetPos.sub(homePos).norm();
                    homeToChase.scaleBy(HOME_RADIUS);
                    desiredPos = homeToChase.add(homePos); // Set the target position to be on the edge of the home bubble, closest to the player
                } else { // Chase the target's position
                    desiredPos = targetPos;
                }
            } else { // If the ghost is not detecting the player
                if(atPoint(entityPos, patrolTarget)) switchPatrolTarget(); // If at a patrol target, swap targets
                desiredPos = patrolTarget; // Move toward the patrol target
            }

            Vector velocity = desiredPos.sub(entityPos).norm();
            velocity.scaleBy(SPEED); // Set the velocity component of the ghost to be the distance vector, normalised and multiplied by speed
            entity.get(VelocityComponent.class).get().set(velocity);
        }

        /**
         * Swap the patrol target of the ghost to the alternate patrol target.
         */
        private void switchPatrolTarget() {
            patrolTarget = (patrolTarget.equals(p1)) ? p2 : p1;
        }

        /**
         * Checks to see if a point "equals" another, within a given threshold.
         * @param location The first position.
         * @param point The second position.
         * @return If a point is close enough to another to be considered equal.
         */
        private boolean atPoint(Vector location, Vector point) {
            return location.dist(point) <= THRESHOLD;
        }


    }
}
