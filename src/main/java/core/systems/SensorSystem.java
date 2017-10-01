package core.systems;

import core.components.ColliderComponent;
import core.scene.SceneUtils;
import core.struct.Sensor;
import core.components.SensorComponent;
import core.entity.GameEntity;
import physics.Collider;
import scew.System;
import scew.World;
import physics.CollisionUtil;
import util.Debug;

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
                    if (component.getEntity().equals(collider.getEntity())) {
                        continue;
                    }

                    Collider c1 = SceneUtils.colliderToWorld(component, sensor);
                    Collider c2 = SceneUtils.colliderToWorld(collider);

                    if (CollisionUtil.intersect(c1, c2)) {

                        // Debug.log(component.getEntity().toString() + " activated by " + collider.getEntity().toString());
                        sensor.sensorListener.onActive(sensor, collider.getEntity());
                    }
                }

            }
        }
    }
}
