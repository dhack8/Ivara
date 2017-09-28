package core.components;

import core.entity.GameEntity;
import eem.Component;
import maths.Vector;

/**
 * Created by Callum Li on 9/15/17.
 */
public class PSpriteComponent extends Component {

    private Vector transform;
    private String resourceID;

    private final boolean hasDimension;

    private Vector dimensions;

    /**
     * Constructor for a sprite component that has a width and height along with a resource identifier.
     * This sprite has a dimension.
     * @param entity parent entity
     * @param resourceID String identifier for the sprite component
     * @param width float value of width
     * @param height float value of height
     */
    public PSpriteComponent(GameEntity entity, String resourceID, float width, float height) {
        super(entity);
        transform = new Vector(0,0);
        this.resourceID = resourceID;

        this.dimensions = new Vector(width, height);

        this.hasDimension = true;
    }

    /**
     * Creates a dimension-less sprite component, rendered at its native resolution.
     * @param entity parent entity
     * @param resourceID String identifier for the sprite component
     */
    public PSpriteComponent(GameEntity entity, String resourceID){
        super(entity);
        transform = new Vector(0,0);
        this.resourceID = resourceID;

        dimensions = new Vector(0,0);
        this.hasDimension = false;
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

    /**
     * Method to get the transform vector.
     * @return the transform vector
     */
    public Vector getTransform() {
        return transform;
    }

    public void setTransform(Vector transform) {
        this.transform = transform;
    }

    public Vector getLocation(){
        Vector superLocation = super.getPosition();
        return new Vector(transform.x + superLocation.x, transform.y + superLocation.y);
    }

    public Vector getDimensions() {
        return dimensions;
    }

    public boolean isDimensionless() {
        return !hasDimension;
    }
}
