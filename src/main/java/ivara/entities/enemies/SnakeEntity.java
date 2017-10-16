package ivara.entities.enemies;

import core.struct.AnimatedSprite;
import maths.Vector;
import java.util.Arrays;

/**
 * Snake entity that patrols back and forth between two points. The snake is able to be killed by the player if player
 * jumps on the snake's head. Kills player the player if it comes into contact with the player.
 * @author David Hack
 * @author Alex Mitchell
 */
public class SnakeEntity extends BasicEnemyEntity implements Enemy {

    // Constants
    private final static float WIDTH = 0.7f;
    private final static float HEIGHT = 1.5f;
    private final static int RATE = 200; // Animation rate

    /**
     * Constructs a snake entity that moves until it detects a collision or no block underneath it.
     * @param location The initial position.
     */
    public SnakeEntity(Vector location){
        super(location, new Vector(WIDTH, HEIGHT));
        AnimatedSprite as = new SnakeSprite(new Vector(WIDTH, HEIGHT), RATE);
        super.setSprite(as);
    }

    /**
     * This class handles the appearance of the Snake Sprite.
     * @author David Hack
     */
    private class SnakeSprite extends AnimatedSprite{
        private SnakeSprite(Vector dimensions, int frameTick){
            super(new Vector(0,0), dimensions, frameTick);
            String state = "normal";
            String[] resources = new String[] {
                    "long-slime",
                    "long-slime2"
            };
            addResources(state, Arrays.asList(resources));
            setState("normal");
        }
    }
}
