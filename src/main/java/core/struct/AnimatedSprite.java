package core.struct;

import maths.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Animated sprite is an extension of Sprite that allows for multiple
 * images to be displayed through one Sprite. This is done by creating
 * the resource map linking the animated sprite's current state to what
 * resources it should display. Each animated sprite can specify how long
 * it should take before it's image rolls over on update to adjust for
 * syncing issues that can arise from animations looking out of place.
 *
 * This is a little bit of a misrepresentation of what a 'struct' should be and could
 * be refactored in the future.
 *
 * @author Will Pearson
 */
public class AnimatedSprite extends Sprite{

    private String state;
    private List<String> resources;
    private int frame;
    private int frameTick;
    private long time;
    private Map<String, List<String>> resourceMap = new HashMap<>();

    /**
     * Constructor does not take any resource ID upon creation as you need
     * to define the map resource map structure that the sprite will use. This
     * is done with the addResource() method.
     * @param transform The animated sprite's relative position.
     * @param dimensions The width and height of the animated sprite.
     * @param frameTick The time taken before the image should switch.
     */
    public AnimatedSprite(Vector transform, Vector dimensions, int frameTick) {
        super(new ResourceID("black-box"), transform, dimensions);
        this.frameTick = frameTick;
    }

    /**
     * Adds a state's resources for the sprite to use.
     * @param state the state of the sprite
     * @param resources the resources relating to the state
     */
    public void addResources(String state, List<String> resources) {
        assert state != null && resources != null && resources.size() > 0;
        if (resourceMap.containsKey(state)) {
            List<String> r = resourceMap.get(state);
            r.addAll(resources);
        } else
            resourceMap.put(state, new ArrayList<>(resources));
    }

    /**
     * Sets the state. If the state isn't in the resource map
     * then it won't be set.
     * if the resources are not empty the ID will be immediately set to a id from the resources.
     * @param newState Must already be stored.
     */
    public void setState(String newState) {
        assert resourceMap.containsKey(newState);

        if (newState.equals(state))
            return;

        this.state = newState;
        this.resources = resourceMap.get(state);
        this.frame = 0;

        if (!resources.isEmpty()){
            super.resourceID = new ResourceID(resources.get(frame));
        }
    }

    /**
     * Updates the currently displayed resource.
     * @param delta time changed since last call
     */
    public void updateResource(long delta) {
        time += delta;
        while (time > frameTick) {
            time -= frameTick;
            frame = (frame+1)%resources.size();
            super.resourceID = new ResourceID(resources.get(frame));
        }

    }

    /**
     * Getter for the state.
     * @return the current state
     */
    public String getState(){
        return state;
    }
}
