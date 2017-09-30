package core.struct;

import maths.Vector;

/**
 * @author David Hack
 */
public class Sprite {

    public final Vector transform;
    public final Vector dimensions;
    public ResourceID resourceID;


    public Sprite(ResourceID resourceID, Vector transform, Vector dimensions) {
        this.transform = transform;
        this.dimensions = dimensions;
        this.resourceID = resourceID;
    }

    public boolean hasDimension(){
        return !(dimensions == null);
    }


}
