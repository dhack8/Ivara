package pxljam.entities;

import core.components.CollidierComponent;
import core.components.SpriteComponent;

/**
 * This abstract class is used tp manage the platform entities
 * @author Alex Mitchell
 */
public abstract class PlatformEntity {
    private SpriteComponent sprite;
    private CollidierComponent collider;
}
