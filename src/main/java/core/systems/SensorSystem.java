package core.systems;

import core.components.ColliderComponent;
import core.struct.Sensor;
import core.components.SensorComponent;
import core.entity.GameEntity;
import eem.System;
import eem.World;
import physics.CollisionUtil;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Callum Li on 9/17/17.
 */
public class SensorSystem extends System<GameEntity> {

    @Override
    public void update(int dt, World<GameEntity> world) {
        Collection<SensorComponent> components = world.get(SensorComponent.class);
        Collection<ColliderComponent> colliders = world.get(ColliderComponent.class);

        // todo: add calls to other listener methods e.g. onEnter

        for (SensorComponent component : components) {
            for (Sensor sensor : component.getSensors()) {
                for (ColliderComponent collider : colliders) {
                    if (component.getEntity().equals(collider)) {
                        continue;
                    }

                    if (CollisionUtil.intersect(sensor.collider, collider.getCollider())) {
                        sensor.sensorListener.onActive(sensor, collider.getEntity());
                    }
                }

            }
        }
    }
}
