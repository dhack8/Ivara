package core.systems;

import core.scene.SceneUtils;
import core.components.ColliderComponent;
import core.components.PhysicsComponent;
import core.entity.GameEntity;
import scew.System;
import scew.World;
import maths.Vector;
import physics.AABBCollider;
import physics.Collider;
import physics.CollisionUtil;
import physics.PhysicProperties;

import java.util.HashMap;

/**
 * Created by Callum Li on 9/29/17.
 */
public class PhysicsSystem extends System<GameEntity> {

    private HashMap<ColliderComponent, PhysicProperties> memo;

    @Override
    public void update(int dt, World<GameEntity> world) {
        ColliderComponent[] colliders = world.get(ColliderComponent.class).toArray(new ColliderComponent[0]);

        memo = new HashMap<>();

        for (int i = 0; i < colliders.length; i++) {
            for (int j = i+1; j < colliders.length; j++) {
                ColliderComponent cc1 = colliders[i];
                ColliderComponent cc2 = colliders[j];

                resolveCollision(cc1, cc2);
            }
        }

        for (int i = colliders.length - 1; i >= 0; i--) {
            for (int j = i-1; j >= 0; j--) {
                ColliderComponent cc1 = colliders[i];
                ColliderComponent cc2 = colliders[j];

                resolveCollision(cc1, cc2);
            }
        }
    }

    private void resolveCollision(ColliderComponent cc1, ColliderComponent cc2) {
        if (!memo.containsKey(cc1)) {
            memo.put(cc1,
                    cc1.getEntity()
                            .get(PhysicsComponent.class)
                            .map((c) -> c.getProperties())
                            .orElse(PhysicProperties.DEFAULT));
        }
        if (!memo.containsKey(cc2)) {
            memo.put(cc2,
                    cc2.getEntity()
                            .get(PhysicsComponent.class)
                            .map((c) -> c.getProperties())
                            .orElse(PhysicProperties.DEFAULT));
        }

        PhysicProperties p1 = memo.get(cc1);
        PhysicProperties p2 = memo.get(cc2);

        if (p1.type == PhysicProperties.Type.STATIC &&
                p2.type == PhysicProperties.Type.STATIC) {
            return;
        }

        Collider c1 = SceneUtils.colliderToWorld(cc1);
        Collider c2 = SceneUtils.colliderToWorld(cc2);

        if (CollisionUtil.intersect(c1, c2)) {
            Vector v = CollisionUtil.minimumDistanceVector((AABBCollider) c1, (AABBCollider) c2);

            if (p2.getType() != PhysicProperties.Type.STATIC ) {
                cc2.getEntity().getTransform().incrementBy(v.x, v.y);
            }
            if (p1.getType() != PhysicProperties.Type.STATIC) {
                cc1.getEntity().getTransform().incrementBy(-v.x, -v.y);
            }
        }
    }
}
