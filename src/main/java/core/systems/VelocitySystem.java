package core.systems;

import core.components.VelocityComponent;
import core.entity.GameEntity;
import scew.System;
import scew.World;

import java.io.Serializable;

/**
 * Moves entities with velocity components.
 * @author Callum Li & David Hack
 */
public class VelocitySystem extends System<GameEntity> implements Serializable {

    /**
     * Main update loop, updates transforms by un paused velocities.
     * @param dt time passed
     * @param world the game world
     */
    @Override
    public void update(int dt, World<GameEntity> world) {
        world.get(VelocityComponent.class).stream()
                .filter((c) -> !c.isPaused())
                .forEach((c) -> c.getEntity()
                        .getTransform()
                        .incrementBy(
                                c.getVelocity().x * (dt/1000f),
                                c.getVelocity().y * (dt/1000f)
                        ));
    }
}
