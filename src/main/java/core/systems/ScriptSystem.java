package core.systems;

import core.components.ScriptComponent;
import core.entity.GameEntity;
import scew.System;
import scew.World;

/**
 * Created by Callum Li on 9/29/17.
 */
public class ScriptSystem extends System<GameEntity> {

    @Override
    public void update(int dt, World<GameEntity> world) {
        world.get(ScriptComponent.class).stream()
                .forEach((s) -> s.getScripts().forEach((sc) -> sc.update(dt, s.getEntity())));
    }
}
