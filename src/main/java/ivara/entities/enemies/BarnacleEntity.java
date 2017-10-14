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
 * Barnacle entity that remains stationary and damages the player if they come in contact with it.
 */
public class BarnacleEntity extends GameEntity implements ImmortalEnemy {

    private float width = 0.5f;
    private float height = 0.7f;

    private String dir = "north";

    private final static int ANIMATION_RATE = 400;

    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public BarnacleEntity(Vector transform, boolean snapToGrid) {
        super(transform);
        Vector dimension = new Vector(width, height);

        if (snapToGrid)
            snapToGrid(transform, NORTH);

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

        //Layer---
        addComponent(new RenderComponent(this, 1100));

        //Collider--
        Vector topLeft = new Vector(0,0);
        addComponent(new ColliderComponent(this, new AABBCollider(AABBCollider.MIN_DIM, topLeft, dimension)));

        //Sprite---
        dir = direction.name().toLowerCase();
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new BarnacleSprite(dimension, ANIMATION_RATE));
        addComponent(sc);
    }

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

    private class BarnacleSprite extends AnimatedSprite {
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

