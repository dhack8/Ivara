package ivara.entities.sprites;

import core.struct.AnimatedSprite;
import maths.Vector;

import java.util.Arrays;

public class PlayerSprite extends AnimatedSprite {

    /**
     * Constructor does not take any resource ID upon creation as you need
     * to define the map resource map structure that the sprite will use. This
     * is done with the addResource() method.
     *
     * @param transform  The animated sprite's relative position.
     * @param dimensions The width and height of the animated sprite.
     * @param frameTick  The time taken before the image should switch.
     */
    public PlayerSprite(Vector transform, Vector dimensions, int frameTick) {
        super(transform, dimensions, frameTick);
        String state = "walking-right";
        String[] resources = new String[] {
                "player-walk-right",
                "player-walk2-right"
        };
        addResources(state, Arrays.asList(resources));
        state = "idle-right";
        resources = new String[] {
                "player-right"
        };
        addResources(state, Arrays.asList(resources));
        state = "walking-left";
        resources = new String[] {
                "player-walk-left",
                "player-walk2-left"
        };
        addResources(state, Arrays.asList(resources));
        state = "idle-left";
        resources = new String[] {
                "player-left"
        };
        addResources(state, Arrays.asList(resources));
        setState("walking-left"); //TODO incorporate state changes by key
    }


}
