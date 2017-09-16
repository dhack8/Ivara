package pxljam;

import core.components.ColliderComponent;
import core.components.PSpriteComponent;
import core.scene.Entity;
import maths.Vector;
import physics.AABBCollider;
import pxljam.scripts.PlayerController;

/**
 * Created by Callum Li on 9/16/17.
 */
public class TestEntity extends Entity {

    public TestEntity(Vector position) {
        super(position);

        addComponent(new PlayerController(this));
        addComponent(new PSpriteComponent(this, "player", 1, 1));
        addComponent(new ColliderComponent(this, new AABBCollider(new Vector(0, 0), new Vector(1, 1))));
    }

}
