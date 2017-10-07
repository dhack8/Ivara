package ivara.entities.sprites;

import core.struct.AnimatedSprite;
import maths.Vector;

import java.util.Arrays;

public class PlayerSprite extends AnimatedSprite {

    public final static String WALK_RIGHT = "walk-right";
    public final static String WALK_LEFT = "walk-left";
    public final static String IDLE_RIGHT = "idle-right";
    public final static String IDLE_LEFT = "idle-left";

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
        String state = WALK_RIGHT;
        String[] resources = new String[] {
                "player-walk-right",
                "player-walk2-right"
        };
        addResources(state, Arrays.asList(resources));
        state = IDLE_RIGHT;
        resources = new String[] {
                "player-right"
        };
        addResources(state, Arrays.asList(resources));
        state = WALK_LEFT;
        resources = new String[] {
                "player-walk-left",
                "player-walk2-left"
        };
        addResources(state, Arrays.asList(resources));
        state = IDLE_LEFT;
        resources = new String[] {
                "player-left"
        };
        addResources(state, Arrays.asList(resources));
        setState(IDLE_RIGHT); //TODO incorporate state changes by key
    }
}
