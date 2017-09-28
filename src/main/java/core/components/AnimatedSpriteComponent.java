package core.components;

import core.entity.GameEntity;
import eem.Component;

import java.util.*;

/**
 * TODO: Write this
 * @author Will Pearson
 */
public class AnimatedSpriteComponent extends GameEntityComponent {

    private String state;
    private List<String> resources;
    private int frame;
    private int frameTick;
    private long time;
    private Map<String, List<String>> resourceMap = new HashMap<>();

    /**
     * Constructs an AnimatedSpriteComponent with an entity is applies
     * to and the time it takes to change frame.
     * @param entity The entity
     * @param frameTick Time for frame change.
     */
    public AnimatedSpriteComponent(GameEntity entity, int frameTick) {
        super(entity);
        this.frameTick = frameTick;
    }

    /**
     * Adds a resource for the component to use.
     * @param state the state the resource is used in
     * @param resource the resource
     */
    public void addResource(String state, String resource) {
        assert state != null && resource != null;
        List<String> resources = resourceMap.containsKey(state) ? resourceMap.get(state) : new ArrayList<>();
        assert resources.contains(resource); // Can't add the
        resources.add(resource);
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
     * Gets the resource name that should be displaying.
     * @param delta time changed since last call
     * @return the resource name
     */
    public String getCurrentResource(long delta) {
        time += delta;
        if (time > frameTick) {
            time -= frameTick;
            frame = (frame+1)%resources.size();
        }
        return resources.get(frame);
    }
}
