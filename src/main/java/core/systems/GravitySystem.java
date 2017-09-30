package core.systems;

import core.components.VelocityComponent;
import core.entity.GameEntity;
import eem.System;
import eem.World;
import ivara.scripts.Gravity;
import maths.Vector;

/**
 * Created by Callum Li on 9/30/17.
 */
public class GravitySystem extends System<GameEntity> {

    private final Vector gravity;

    public GravitySystem(Vector gravity) {
        this.gravity = gravity;
    }

    @Override
    public void update(int dt, World<GameEntity> world) {
        world.get(VelocityComponent.class)
                .forEach((c) -> c.add(gravity.x * dt/1000, gravity.y * dt/1000));
    }
}
