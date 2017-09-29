package ivara;

import core.components.ColliderComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import maths.Vector;
import physics.AABBCollider;
import ivara.scripts.PlayerController;

/**
 * Created by Callum Li on 9/16/17.
 */
public class TestEntity extends GameEntity {

    public TestEntity(Vector position) {
        super(position);

        addComponent(new PlayerController(this));
        addComponent(new SpriteComponent(this, "player", 1, 1));
        addComponent(new ColliderComponent(this, new AABBCollider(new Vector(0, 0), new Vector(1, 1))));
    }

}
