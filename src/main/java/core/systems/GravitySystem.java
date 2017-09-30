package core.systems;

import core.components.PhysicsComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import scew.System;
import scew.World;
import maths.Vector;
import util.Debug;

import java.util.Set;
import java.util.stream.Collectors;

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
        Set<GameEntity> physicEntities = world.get(PhysicsComponent.class).stream()
                .map((physicsComponent -> physicsComponent.getEntity()))
                .collect(Collectors.toSet());
        world.get(VelocityComponent.class).stream()
                .filter(velocityComponent -> physicEntities.contains(velocityComponent.getEntity()))
                .forEach((velocityComponent -> velocityComponent.getVelocity().add(gravity.x * (dt/1000f), gravity.y * (dt/1000f))));
    }
}
