package core.systems;

import core.scene.SceneUtils;
import core.components.ColliderComponent;
import core.components.PhysicsComponent;
import core.entity.GameEntity;
import eem.System;
import eem.World;
import maths.Vector;
import physics.AABBCollider;
import physics.Collider;
import physics.CollisionUtil;
import physics.PhysicProperties;

/**
 * Created by Callum Li on 9/29/17.
 */
public class PhysicsSystem extends System<GameEntity> {


    @Override
    public void update(int dt, World<GameEntity> world) {
        ColliderComponent[] colliders = world.get(ColliderComponent.class).toArray(new ColliderComponent[0]);


        for (int i = 0; i < colliders.length; i++) {

            for (int j = 0; j < colliders.length; j++) {
                ColliderComponent cc1 = colliders[i];
                ColliderComponent cc2 = colliders[j];
                PhysicProperties p1 = cc1.getEntity().get(PhysicsComponent.class).getProperties();
                PhysicProperties p2 = cc2.getEntity().get(PhysicsComponent.class).getProperties();

                if (p1.type == PhysicProperties.Type.STATIC &&
                        p2.type == PhysicProperties.Type.STATIC) {
                    continue;
                }

                Collider c1 = SceneUtils.colliderToWorld(cc1);
                Collider c2 = SceneUtils.colliderToWorld(cc2);

                if (CollisionUtil.intersect(c1, c2)) {
                    Vector v = CollisionUtil.minimumDistanceVector((AABBCollider) c1, (AABBCollider) c2);

                    if (p1.inverseMass < p2.inverseMass) {
                        cc2.getEntity().getTransform().add(v.x, v.y);
                    } else {
                        cc1.getEntity().getTransform().add(-v.x, -v.y);
                    }
                }

            }
        }
    }
}
