package ivara.entities;

import core.components.ColliderComponent;
import core.components.ScriptComponent;
import core.components.SpriteComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import maths.Vector;
import physics.AABBCollider;
import ivara.entities.scripts.BasicMoveScript;

/**
 * A block that can move based on a given script
 * @author Alex Mitchell
 */
public class MovingBlockEntity extends GameEntity {

    /**
     * Creates a MovingBlockEntity that moves from a to b over a period of time
     *
     * @param startX The x position of the top-left corner of the platform
     * @param startY The y position of top-left corner of the platform     *
     * @param endX The end x position
     * @param endY The end y position
     * @param recourseID The recourseID to be used for the platform
     * @param time The duration of a move from one side to another
     *
     */
    public MovingBlockEntity(float startX, float startY, float endX, float endY, String recourseID, float time) {
        super(new Vector(startX, startY));
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new ResourceID(recourseID), new Vector(1f,1f));
        addComponent(sc);
       // addComponent(new SpriteComponent(this, recourseID, 1f, 1f));
        addComponent(new ColliderComponent(this, new AABBCollider(new Vector(0.5f, 0.5f), new Vector(0.5f, 0.5f))));

        //VelocityComponent velocity = new VelocityComponent(this);
        addComponent(new VelocityComponent(this));

        //addComponent(new AutomatedMoveController(this, new Vector(endX, endY), time));
        addComponent(new ScriptComponent(this, new BasicMoveScript(this, new Vector(endX, endY), time)));
    }
}