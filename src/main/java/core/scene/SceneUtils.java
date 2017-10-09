package core.scene;

import core.components.ColliderComponent;
import core.components.SensorComponent;
import core.entity.GameEntity;
import core.struct.Sensor;
import maths.Vector;
import physics.AABBCollider;
import physics.Collider;
import scew.Entity;

/**
 * Created by Callum Li on 9/29/17.
 */
public class SceneUtils {

    public static Collider colliderToWorld(Collider collider, GameEntity parent) {
        return collider.translate(parent.getTransform());
    }

    public static Collider colliderToWorld(ColliderComponent colliderComponent) {
        Collider collider = colliderComponent.getCollider();
        Vector transform = colliderComponent.getEntity().getTransform();

        return collider.translate(transform);
    }

    public static Collider colliderToWorld(SensorComponent component, Sensor sensor) {
        Collider collider = sensor.collider;
        Vector transform = component.getEntity().getTransform();

        return collider.translate(transform);
    }
}
