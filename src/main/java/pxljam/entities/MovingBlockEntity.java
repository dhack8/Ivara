package pxljam.entities;

import core.components.ColliderComponent;
import core.components.PSpriteComponent;
import core.entity.Entity;
import maths.Vector;
import physics.AABBCollider;
import pxljam.scripts.AutomatedMoveController;
import pxljam.scripts.LeftRightController;

/**
 * A block that can move based on a given script
 * @author Alex Mitchell
 */
public class MovingBlockEntity extends Entity {

    /**
     * Creates a Basic Platform Entity at a specified position
     *
     * @param x          The x position of the top-left corner of the platform
     * @param y          The y position of top-left corner of the platform
     * @param recourseID the recourseID to be used for the platform
     */
    public MovingBlockEntity(float x, float y, String recourseID, float xDistance, float yDistace) {
        super(new Vector(x, y));
        addComponent(new PSpriteComponent(this, recourseID, 1, 1));
        addComponent(new ColliderComponent(this, new AABBCollider(new Vector(0.5f, 0.5f), new Vector(0.5f, 0.5f))));
        addComponent(new AutomatedMoveController(this, xDistance ,yDistace));
    }
}