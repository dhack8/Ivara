package pxljam.entities;

import core.components.ColliderComponent;
import core.components.SpriteComponent;
import core.scene.Entity;
import maths.Vector;
import physics.Collider;

/**
 * This class handles the basic platform entity
 * @author Alex Mitchell
 */
public class BasicPlatformEntity extends Entity{
    private SpriteComponent sprite;
    private ColliderComponent collider;

    /**
     * Creates a Basic Platform Entity at a specified position
     * @param x The x position of the top-left corner of the platform
     * @param y The y position of top-left corner of the platform
     */
    public BasicPlatformEntity(float x, float y){
        super(new Vector(x,y));
        Vector v = new Vector(x,y);
        sprite = new SpriteComponent();
        collider = new ColliderComponent();
    }
}
