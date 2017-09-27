package ivara.entities;

import core.components.ColliderComponent;
import core.entity.Entity;
import core.components.PSpriteComponent;
import maths.Vector;
import physics.AABBCollider;

/**
 * This class handles the basic platform entity
 * @author Alex Mitchell
 */
public class BasicBlockEntity extends Entity{

    /**
     * Creates a Basic Platform Entity at a specified position
     * @param x The x position of the top-left corner of the platform
     * @param y The y position of top-left corner of the platform
     * @param recourseID the recourseID to be used for the platform
     */
    public BasicBlockEntity(float x, float y, String recourseID){
        super(new Vector(x,y));
        addComponent(new PSpriteComponent(this, recourseID, 1, 1));
        addComponent(new ColliderComponent(this, new AABBCollider(new Vector(0.5f, 0.5f), new Vector(0.5f, 0.5f))));
    }
}
