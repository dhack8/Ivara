package pxljam.entities;

import core.components.ColliderComponent;
import core.components.PSpriteComponent;
import core.scene.Entity;
import maths.Vector;

/**
 * This abstract class is used tp manage the platform entities
 * @author Alex Mitchell
 */
public abstract class PlatformEntity extends Entity{
    private PSpriteComponent sprite;
    private ColliderComponent collider;

    public PlatformEntity(Vector v, PSpriteComponent sprite, ColliderComponent collider){
        super(v);
        this.sprite = sprite;
        this.collider = collider;
    }
}
