package core.systems;

import core.components.VelocityComponent;
import core.entity.GameEntity;
import scew.System;
import scew.World;

/**
 * Created by Callum Li on 9/17/17.
 */
public class VelocitySystem extends System<GameEntity> {


    @Override
    public void update(int dt, World<GameEntity> world) {

        world.get(VelocityComponent.class).stream()
                .filter((c) -> !c.isPaused())
                .forEach((c) -> c.getEntity()
                        .getTransform()
                        .add(
                                c.getVelocity().x * (dt/1000f),
                                c.getVelocity().y * (dt/1000f)
                        ));

    }
}
