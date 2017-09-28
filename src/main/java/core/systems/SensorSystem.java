package core.systems;

import core.components.ColliderComponent;
import core.components.SensorComponent;
import core.entity.EntityContainer;
import core.entity.GameEntity;
import eem.System;
import eem.World;
import physics.Collider;
import physics.CollisionUtil;
import physics.EntitySystem;

import java.util.Collection;

/**
 * Created by Callum Li on 9/17/17.
 */
public class SensorSystem extends System<GameEntity> {

    @Override
    public void update(int dt, World<GameEntity> world) {
        Collection<SensorComponent> sensors = world.get(SensorComponent.class);
        Collection<ColliderComponent> colliders = world.get(ColliderComponent.class);

        // todo: add calls to other listener methods e.g. onEnter

        for (SensorComponent sensor : sensors) {
            for (ColliderComponent collider : colliders) {
                if (sensor.getEntity().equals(collider.getEntity())) {
                    continue;
                }

                if (CollisionUtil.intersect(sensor.getCollider(), collider.getCollider())) {
                    sensor.getListener().active(collider.getEntity());
                }
            }
        }
    }
}
