package core.components;

import core.scene.Entity;
import maths.Vector;

/**
 * Created by Callum Li on 9/15/17.
 */
public class PSpriteComponent extends Component {

    private Vector transform;
    private final String resourceID;
    private final float width;
    private final float height;

    public PSpriteComponent(Entity entity, String resourceID, float width, float height) {
        super(entity);
        transform = new Vector(0,0);
        this.resourceID = resourceID;

        this.width = width;
        this.height = height;
    }

    /**
     * Method to get the resource identifier.
     * @return the resourceID
     */
    public String getResourceID() {
        return resourceID;
    }

    /**
     * Method to get the transform vector.
     * @return the transform vector
     */
    public Vector getTransform() {
        return transform;
    }


    public Vector getLocation(){
        Vector superLocation = super.getPosition();
        return new Vector(transform.x + superLocation.x, transform.y + superLocation.y);
    }
}
