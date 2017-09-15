package pxljam.entities;

import core.components.CollidierComponent;
import core.components.SpriteComponent;
import core.scene.Entity;

/**
 * This abstract class is used tp manage the platform entities
 * @author Alex Mitchell
 */
public abstract class PlatformEntity extends Entity{
    private SpriteComponent sprite;
    private CollidierComponent collider;
}
