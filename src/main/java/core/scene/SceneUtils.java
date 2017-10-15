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
 * Generic transform helpers.
 * @author Callum Li
 */
public class SceneUtils {

    /**
     * Returns a collider transformed by parent entity/
     * @param collider collider to transform
     * @param parent parent it belongs to
     * @return transformed collider
     */
    public static Collider colliderToWorld(Collider collider, GameEntity parent) {
        return collider.translate(parent.getTransform());
    }

    /**
     * Transforms a whole collider component.
     * @param colliderComponent collider component to get collider is correct pos
     * @return transformed collider
     */
    public static Collider colliderToWorld(ColliderComponent colliderComponent) {
        Collider collider = colliderComponent.getCollider();
        Vector transform = colliderComponent.getEntity().getTransform();

        return collider.translate(transform);
    }

    /**
     * Returns a collider transformed by parent entity/
     * @param sensor collider to transform (sensors are colliders)
     * @param component parent it belongs to
     * @return transformed collider
     */
    public static Collider colliderToWorld(SensorComponent component, Sensor sensor) {
        Collider collider = sensor.collider;
        Vector transform = component.getEntity().getTransform();

        return collider.translate(transform);
    }
}
