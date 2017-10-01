package ivara.entities;

import core.components.SpriteComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import maths.Vector;

/**
 * Created by Callum Li on 10/1/17.
 */
public class BulletEntity extends GameEntity {

    public BulletEntity(Vector transform, Vector end, int dt) {
        super(transform);
        Vector velocity = new Vector((end.x - transform.x)*(1000/dt), (end.x - transform.x)*(1000/dt));

        addComponent(new VelocityComponent(this, velocity));

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new ResourceID("player"), new Vector(1f, 1.5f));
        addComponent(sc);
    }
}
