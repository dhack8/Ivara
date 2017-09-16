package pxljam;

import core.components.ColliderComponent;
import core.components.PSpriteComponent;
import core.entity.Entity;
import maths.Vector;
import physics.AABBCollider;

/**
 * Created by Callum Li on 9/16/17.
 */
public class TestEntity2 extends Entity {

    public TestEntity2(Vector position) {
        super(position);

        //addComponent(new TestScript(this));
        addComponent(new PSpriteComponent(this, "player"));
        addComponent(new ColliderComponent(this, new AABBCollider(new Vector(0, 0), new Vector(15, 15))));
    }

}
