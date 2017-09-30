package core.struct;

import core.entity.GameEntity;
import maths.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimatedSprite extends Sprite{
    private String state;
    private List<String> resources;
    private int frame;
    private int frameTick;
    private long time;
    private Map<String, List<String>> resourceMap = new HashMap<>();

    public AnimatedSprite(Vector transform, Vector dimensions, int frameTick) {
        super(new ResourceID(""), transform, dimensions);
        this.frameTick = frameTick;
    }

    /**
     * Adds a state's resources for the sprite to use.
     * @param state the state of the sprite
     * @param resources the resources relating to the state
     */
    public void addResources(String state, List<String> resources) {
        assert state != null && resources != null && resources.size() > 0;
        assert resourceMap.containsKey(state);
        resourceMap.put(state, resources);
    }

    /**
     * Sets the state. If the state isn't in the resource map
     * then it won't be set.
     * @param newState Must already be stored.
     */
    public void setState(String newState) {
        assert resourceMap.containsKey(newState);
        this.state = newState;
        this.resources = resourceMap.get(state);
    }

    /**
     * Updates the currently displayed resource.
     * @param delta time changed since last call
     */
    public void updateResource(long delta) {
        time += delta;
        if (time > frameTick) {
            time -= frameTick;
            frame = (frame+1)%resources.size();
        }
        super.resourceID = new ResourceID(resources.get(frame));
    }
}
