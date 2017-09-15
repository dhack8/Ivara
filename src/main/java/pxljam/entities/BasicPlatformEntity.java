package pxljam.entities;

import core.components.ColliderComponent;
import core.components.SpriteComponent;
import maths.Vector;
import physics.Collider;

/**
 * This class handles the basic platform entity
 * @author Alex Mitchell
 */
public class BasicPlatformEntity extends PlatformEntity{

    public BasicPlatformEntity(Vector v, SpriteComponent s, ColliderComponent c){
        super(v,s,c);
    }
}
