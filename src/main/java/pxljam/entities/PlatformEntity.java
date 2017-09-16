package pxljam.entities;

import core.components.ColliderComponent;
import core.components.SpriteComponent;
import core.scene.Entity;
import maths.Vector;

/**
 * This abstract class is used tp manage the platform entities
 * @author Alex Mitchell
 */
public abstract class PlatformEntity extends Entity{
    private SpriteComponent sprite;
    private ColliderComponent collider;

    public PlatformEntity(Vector v, SpriteComponent sprite, ColliderComponent collider){
        super(v);
        this.sprite = sprite;
        this.collider = collider;
    }
}
