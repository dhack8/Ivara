package pxljam.entities;

import core.components.ColliderComponent;
import core.scene.Entity;
import core.components.PSpriteComponent;
import maths.Vector;

/**
 * This class handles the basic platform entity
 * @author Alex Mitchell
 */
public class BasicPlatformEntity extends Entity{
    private PSpriteComponent sprite;
    private ColliderComponent collider;

    /**
     * Creates a Basic Platform Entity at a specified position
     * @param x The x position of the top-left corner of the platform
     * @param y The y position of top-left corner of the platform
     */
    public BasicPlatformEntity(float x, float y){
        super(new Vector(x,y));
        Vector v = new Vector(x,y);
        sprite = new PSpriteComponent(this, "grass-top"); // Todo change the PImage
        collider = new ColliderComponent(this, null); // Todo change the Collider
    }
}
