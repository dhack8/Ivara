package core.components;

import core.entity.GameEntity;
import eem.Component;
import maths.Vector;

/**
 * Created by Callum Li on 9/15/17.
 */
public class SpriteComponent extends Component<GameEntity> {

    public final Vector transform;
    private final Vector dimensions;
    private String resourceID;

    /**
     * Constructor for a sprite component that has a width and height along with a resource identifier.
     * This sprite has a dimension.
     * @param entity parent entity
     * @param resourceID String identifier for the sprite component
     * @param width float value of width
     * @param height float value of height
     */
    public SpriteComponent(GameEntity entity, String resourceID, float width, float height) {
        super(entity);
        transform = new Vector(0,0);
        this.dimensions = new Vector(width, height);
        this.resourceID = resourceID;
    }

    /**
     * Constructor for a sprite component that has a width and height along with a resource identifier.
     * This sprite has a dimension.
     * @param entity parent entity
     * @param resourceID String identifier for the sprite component
     * @param width float value of width
     * @param height float value of height
     * @param transform the transformation vector
     */
    public SpriteComponent(GameEntity entity, String resourceID, float width, float height, Vector transform) {
        super(entity);
        this.transform = transform;
        this.dimensions = new Vector(width, height);
        this.resourceID = resourceID;
    }


    /**
     * Creates a dimension-less sprite component, rendered at its native resolution.
     * @param entity parent entity
     * @param resourceID String identifier for the sprite component
     */
    public SpriteComponent(GameEntity entity, String resourceID){
        super(entity);
        this.transform = new Vector(0,0);
        this.dimensions = null;
        this.resourceID = resourceID;
    }

    /**
     * Method to get the resource identifier.
     * @return the resourceID
     */
    public String getResourceID() {
        return resourceID;
    }

    /**
     * Sets the resourceID.
     * @param resourceID resourceID
     */
    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public Vector getLocation(){
        Vector superLocation = getEntity().getTransform();
        return new Vector(transform.x + superLocation.x, transform.y + superLocation.y);
    }

    public Vector getDimensions() {
        return dimensions;
    }

    public boolean isDimensionless() {
        return dimensions == null;
    }
}
