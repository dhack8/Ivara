package core.systems;

import core.components.VelocityComponent;
import core.entity.GameEntity;
import scew.System;
import scew.World;
import maths.Vector;
import util.Debug;

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
        Debug.log(Float.toString(gravity.y * (dt/1000f)));
        world.get(VelocityComponent.class)
                .forEach((c) -> c.add(gravity.x * (dt/1000f), gravity.y * (dt/1000f)));
    }
}
