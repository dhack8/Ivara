package ivara.entities;

import core.struct.AnimatedSprite;
import core.struct.Sprite;
import maths.Vector;

import java.util.Arrays;

/**
 * Created by David Hack Local on 08-Oct-17.
 */
public class SnakeEntity extends BasicEnemyEntity{

    private final static float WIDTH = 1f;
    private final static float HEIGHT = 3f;
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
