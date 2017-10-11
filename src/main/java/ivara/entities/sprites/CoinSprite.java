package ivara.entities.sprites;

import core.struct.AnimatedSprite;
import maths.Vector;

import java.util.Arrays;

/**
 * Animated coin sprite.
 * @author DAvid Hack
 */
public class CoinSprite extends AnimatedSprite{
    private CoinSprite(Vector transform, Vector dimensions, int frameTick){
        super(transform, dimensions, frameTick);

        String state = "normal";
        String[] resources = new String[] {
                "coin",
                "coin2",
                "coin3",
                "coin4",
                "coin5",
                "coin6",
                "coin7",
                "coin8",
        };
        addResources(state, Arrays.asList(resources));

        setState("normal");
    }
}
