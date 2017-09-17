package core.systems;

import core.components.ColliderComponent;
import core.components.SensorComponent;
import core.entity.EntityContainer;
import physics.CollisionUtil;
import physics.EntitySystem;

import java.util.Collection;

/**
 * Created by Callum Li on 9/17/17.
 */
public class SensorSystem extends EntitySystem {

    public SensorSystem(EntityContainer entities) {
        super(entities);
    }

    @Override
    public void update(long delta) {
        Collection<SensorComponent> sensorComponents = getEntities().getAllComponents(SensorComponent.class);
        Collection<ColliderComponent> colliderComponents = getEntities().getAllComponents(ColliderComponent.class);

        for (SensorComponent s : sensorComponents) {
            for (ColliderComponent c: colliderComponents) {
                if (c.getEntity().equals(s.getEntity())) {
                    continue;
                }

                if (CollisionUtil.intersect(s.getCollider(), c.getCollider())) {
                    s.onInersect(c.getEntity());
                }
            }
        }
    }
}
