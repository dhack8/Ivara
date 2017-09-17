package physics;

import core.components.VelocityComponent;
import core.entity.Entity;
import core.entity.EntityContainer;

/**
 * Created by Callum Li on 9/17/17.
 */
public class VelocitySystem extends EntitySystem {

    public VelocitySystem(EntityContainer entities) {
        super(entities);
    }

    @Override
    public void update(long delta) {
        for (VelocityComponent c : getEntities().getAllComponents(VelocityComponent.class)) {
            c.getEntity().translate(c.getVelocity().mult(delta/1000f));
        }
    }
}
