package pxljam.entities;

import core.components.ColliderComponent;
import core.components.PSpriteComponent;
import maths.Vector;

/**
 * This class handles the basic platform entity
 * @author Alex Mitchell
 */
public class BasicPlatformEntity extends PlatformEntity{

    public BasicPlatformEntity(Vector v, PSpriteComponent s, ColliderComponent c){
        super(v,s,c);
    }
}
