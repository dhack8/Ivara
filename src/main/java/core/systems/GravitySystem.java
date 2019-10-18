package core.systems;

import core.components.PhysicsComponent;
import core.components.VelocityComponent;
import core.entity.GameEntity;
import scew.System;
import scew.World;
import maths.Vector;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Gravity system goes through all entities and if they have the appropriate physics component applies gravity to it.
 * @author Callum Li
 */
public class GravitySystem extends System<GameEntity> implements Serializable {

    private final Vector gravity;

    /**
     * Create Gravity system with a gravity vector.
     * @param gravity gravity vector
     */
    public GravitySystem(Vector gravity) {
        this.gravity = gravity;
    }

    /**
     * Main update loop as outlined above
     * @param dt time passed
     * @param world the game world
     */
    @Override
    public void update(int dt, World<GameEntity> world) {

        Set<GameEntity> physicEntities = world.get(PhysicsComponent.class).stream()
                .map((physicsComponent -> physicsComponent.getEntity()))
                .collect(Collectors.toSet());

        world.get(VelocityComponent.class).stream()
                .filter(velocityComponent -> physicEntities.contains(velocityComponent.getEntity()))
                .forEach((velocityComponent -> velocityComponent.getVelocity().incrementBy(gravity.x * (dt/1000f), gravity.y * (dt/1000f))));
    }
}
