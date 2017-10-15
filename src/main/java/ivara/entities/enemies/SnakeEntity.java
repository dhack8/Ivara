package ivara.entities.enemies;

import core.struct.AnimatedSprite;
import ivara.entities.enemies.BasicEnemyEntity;
import ivara.entities.enemies.Enemy;
import maths.Vector;

import java.util.Arrays;

/**
 * Snake entity that damages the player if ran into. Can be stationary or patrol back and forth between two points.
 */
public class SnakeEntity extends BasicEnemyEntity implements Enemy {

    private final static float WIDTH = 0.7f;
    private final static float HEIGHT = 1.5f;
    private final static int RATE = 200;

    public SnakeEntity(Vector location){
        super(location, new Vector(WIDTH, HEIGHT));
        AnimatedSprite as = new SlimeSprite(new Vector(WIDTH, HEIGHT), RATE);
        super.setSprite(as);
    }

    private class SlimeSprite extends AnimatedSprite{
        private SlimeSprite(Vector dimensions, int frameTick){
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
