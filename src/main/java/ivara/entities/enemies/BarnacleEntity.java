package ivara.entities.enemies;

import core.components.ColliderComponent;
import core.components.RenderComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import maths.Vector;
import physics.AABBCollider;

import java.util.Arrays;

import static ivara.entities.enemies.BarnacleEntity.Direction.*;

/**
 * This class handles the Barnacle entity that remains stationary and kills the player if they come in contact with it.
 * @author David Hack
 * @author Will Pearson
 */
public class BarnacleEntity extends GameEntity implements ImmortalEnemy {

    private final static int ANIMATION_RATE = 400;
    private float width = 0.5f;
    private float height = 0.7f;
    private String dir = "north";

    /**
     * Directions used to change the direction that a Barnacle is facing.
     */
    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    /**
     * Constructs a BarnacleEntity at a specified position.
     * The barnacle can either be floating or snap to the block below it's orientation.
     * @param transform The initial location.
     * @param snapToGrid Whether the barnacle snaps to the block below.
     */
    public BarnacleEntity(Vector transform, boolean snapToGrid) {
        super(transform);
        Vector dimension = new Vector(width, height);

        if (snapToGrid)
            snapToGrid(transform, NORTH);

        // Layer
        addComponent(new RenderComponent(this, 1100));

        // Collider
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        // Sprite
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new BarnacleSprite(dimension, ANIMATION_RATE));
        addComponent(sc);
    }

    /**
     * Constructs a BarnacleEntity at a specified position.
     * The barnacle can either be floating or snap to the block below it's orientation.
     * This constructor takes a direction that changes how the barnacle is oriented.
     * @param transform The initial location.
     * @param snapToGrid Whether the barnacle snaps to the block below.
     * @throws IllegalArgumentException When a direction is invalid.
     */
    public BarnacleEntity(Vector transform, Direction direction, boolean snapToGrid){
        super(transform);
        if (direction == null)
            throw new IllegalArgumentException("Must provide a direction (NORTH, EAST, SOUTH, WEST)");

        if (direction == WEST || direction == EAST) {
            float tmp = width;
            width = height;
            height = tmp;
        }
        Vector dimension = new Vector(width, height);

        if (snapToGrid)
            snapToGrid(transform, direction);

        // Layer
        addComponent(new RenderComponent(this, 1100));

        // Collider
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        // Sprite
        dir = direction.name().toLowerCase();
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new BarnacleSprite(dimension, ANIMATION_RATE));
        addComponent(sc);
    }

    /**
     * Alter the orientation of the barnacle.
     * @param transform The current position.
     * @param dir The direction.
     */
    private void snapToGrid(Vector transform, Direction dir) {
        switch (dir) {
            case NORTH:
                transform.x = ((int) transform.x) + 0.5f - width / 2;
                transform.y = ((int) transform.y)+ 1 - height;
                break;
            case SOUTH:
                transform.x = ((int) transform.x) + 0.5f - width / 2;
                transform.y = ((int) transform.y);
                break;
            case EAST:
                transform.x = ((int) transform.x);
                transform.y = ((int) transform.y) + 0.5f - height / 2;
                break;
            case WEST:
                transform.x = ((int) transform.x) + 1 - width;
                transform.y = ((int) transform.y) + 0.5f - height / 2;
                break;
        }
    }

    /**
     * This class handles the sprite displayed on the Barnacle.
     * @author David Hack
     */
    private class BarnacleSprite extends AnimatedSprite {
        /**
         * Constructs a Sprite for the Barnacle that changes over a frame tick.
         * @param dimensions The dimensions of the sprite.
         * @param frameTick The animation tick.
         */
        private BarnacleSprite(Vector dimensions, int frameTick){
            super(new Vector(0,0), dimensions, frameTick);

            String state = "normal";
            String[] resources = new String[]{
                    "barnacle-" + dir,
                    "barnacle-" + dir + "2"
            };
            addResources(state, Arrays.asList(resources));

            setState("normal");
        }
    }
}

