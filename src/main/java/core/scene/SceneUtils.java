package core.scene;

import core.components.ColliderComponent;
import maths.Vector;
import physics.AABBCollider;
import physics.Collider;

/**
 * Created by Callum Li on 9/29/17.
 */
public class SceneUtils {

    public static Collider colliderToWorld(ColliderComponent colliderComponent) {
        Collider collider = colliderComponent.getCollider();
        Vector transform = colliderComponent.getEntity().getTransform();

        return collider.translate(transform);
    }
}
