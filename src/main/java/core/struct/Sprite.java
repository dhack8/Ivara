package core.struct;

import maths.Vector;

import java.io.Serializable;

/**
 * Basic data object for a sprite
 * @author David Hack
 */
public class Sprite implements Serializable {

    public final Vector transform;
    public final Vector dimensions;
    public ResourceID resourceID;

    /**
     * Creates a sprite data struct.
     * @param resourceID resourceID
     * @param transform transform from the parent entity
     * @param dimensions dimensions of sprite in meters, null if dimensionless
     */
    public Sprite(ResourceID resourceID, Vector transform, Vector dimensions) {
        this.transform = transform;
        this.dimensions = dimensions;
        this.resourceID = resourceID;
    }

    /**
     * Checks if the sprite has a dimension.
     * @return true if the sprite has a dimension
     */
    public boolean hasDimension(){
        return !(dimensions == null);
    }
}
