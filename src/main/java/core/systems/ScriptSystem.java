package core.systems;

import core.components.ScriptComponent;
import core.entity.GameEntity;
import eem.System;
import eem.World;

/**
 * Created by Callum Li on 9/29/17.
 */
public class ScriptSystem extends System<GameEntity> {
/**
    @Override
    public void update(int dt, World<GameEntity> world) {
        world.get(ScriptComponent.class).stream()
                .forEach((s) -> s.script.update(dt, s.getEntity()));
    }
**/
    @Override
    public void update(int dt, World<GameEntity> world) {
        world.get(ScriptComponent.class).stream()
                .forEach((s) -> s.scripts.forEach((sc) -> sc.update(dt, s.getEntity())));
    }
}
