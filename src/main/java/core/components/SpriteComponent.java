package core.components;

import core.entity.GameEntity;
import core.struct.Sprite;
import eem.Component;
import maths.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Callum Li on 9/15/17.
 */
public class SpriteComponent extends Component<GameEntity> {


    private final List<Sprite> sprites = new ArrayList<>();

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
        Sprite s = new Sprite(new Vector(0, 0), new Vector(width, height), resourceID);
        sprites.add(s);
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
        Sprite s = new Sprite(transform, new Vector(width, height), resourceID);
        sprites.add(s);
    }


    /**
     * Creates a dimension-less sprite component, rendered at its native resolution.
     * @param entity parent entity
     * @param resourceID String identifier for the sprite component
     */
    public SpriteComponent(GameEntity entity, String resourceID){
        super(entity);
        Sprite s = new Sprite(new Vector(0, 0), new Vector(0, 0), resourceID);
        sprites.add(s);
    }

//    /**
//     * Method to get the resource identifier.
//     * @return the resourceID
//     */
//    public String getResourceID() {
//        return resourceID;
//    }
//
//    /**
//     * Sets the resourceID.
//     * @param resourceID resourceID
//     */
//    public void setResourceID(String resourceID) {
//        this.resourceID = resourceID;
//    }
//
//    /**
//     * Method to get the transform vector.
//     * @return the transform vector
//     */
//    public Vector getTransform() {
//        return transform;
//    }
//
//    public Vector getLocation(){
//        Vector superLocation = getEntity().getTransform();
//        return new Vector(transform.x + superLocation.x, transform.y + superLocation.y);
//    }

<<<<<<< HEAD
=======
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
>>>>>>> 8f071bf089af6405092b6a3b56b42b3dd0d98ab1
}
