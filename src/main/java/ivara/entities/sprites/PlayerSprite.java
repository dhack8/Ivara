package ivara.entities.sprites;

import core.struct.AnimatedSprite;
import maths.Vector;

import java.util.Arrays;

public class PlayerSprite extends AnimatedSprite {

    public final static String WALK_RIGHT = "walk-right";
    public final static String WALK_LEFT = "walk-left";
    public final static String IDLE_RIGHT = "idle-right";
    public final static String IDLE_LEFT = "idle-left";
    public final static String JUMP_RIGHT = "jump-right";
    public final static String JUMP_LEFT = "jump-left";
    public final static String DUCK_RIGHT = "duck-right";
    public final static String DUCK_LEFT = "duck-left";

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
                "player-walk2-right",
                "player-walk-right"
        };
        addResources(state, Arrays.asList(resources));

        state = IDLE_RIGHT;
        resources = new String[] {
                "player-right"
        };
        addResources(state, Arrays.asList(resources));

        state = JUMP_RIGHT;
        resources = new String[] {
                "player-jump-right"
        };
        addResources(state, Arrays.asList(resources));

        state = DUCK_RIGHT;
        resources = new String[] {
                "player-duck-right"
        };
        addResources(state, Arrays.asList(resources));

        state = WALK_LEFT;
        resources = new String[] {
                "player-walk2-left",
                "player-walk-left"
        };
        addResources(state, Arrays.asList(resources));

        state = IDLE_LEFT;
        resources = new String[] {
                "player-left"
        };
        addResources(state, Arrays.asList(resources));

        state = JUMP_LEFT;
        resources = new String[] {
                "player-jump-left"
        };
        addResources(state, Arrays.asList(resources));

        state = DUCK_LEFT;
        resources = new String[] {
                "player-duck-left"
        };
        addResources(state, Arrays.asList(resources));

        //TODO @will sprite animation: incorporate state changes by key
        setState(IDLE_RIGHT);
    }
}
