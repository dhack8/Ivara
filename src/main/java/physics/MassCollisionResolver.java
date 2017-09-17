package physics;

import core.components.ColliderComponent;
import core.entity.EntityContainer;
import maths.Vector;

/**
 * Created by Callum Li on 9/17/17.
 */
public class MassCollisionResolver {

    private EntityContainer entities;

    public MassCollisionResolver(EntityContainer entities) {
        this.entities = entities;
    }


    public void resolveCollisions() {
        ColliderComponent[] colliders = entities.getAllComponents(ColliderComponent.class).toArray(new ColliderComponent[0]);

        for (int i = 0; i < colliders.length; i++) {
            for (int j = i+1; j < colliders.length; j++) {
                ColliderComponent c1 = colliders[i];
                ColliderComponent c2 = colliders[j];

                if (CollisionUtil.intersect(c1.getCollider(), c2.getCollider())) {

                    System.out.println(c1.getEntity() + " is colliding with " + c2.getEntity());

                    Vector v = CollisionUtil.minimumDistanceVector((AABBCollider) c1.getCollider(), (AABBCollider) c2.getCollider());
                    c1.getEntity().translate(-v.x, -v.y);
                }
            }
        }

    }
}
