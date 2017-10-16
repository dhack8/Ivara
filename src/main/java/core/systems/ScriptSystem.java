package core.systems;

import core.components.ScriptComponent;
import core.entity.GameEntity;
import scew.System;
import scew.World;

/**
 * Script system is basic, it updates every script with the relevant information.
 * @author Callum Li
 */
public class ScriptSystem extends System<GameEntity> {

    /**
     * Main update loop as outlined above
     * @param dt time passed
     * @param world the game world
     */
    @Override
    public void update(int dt, World<GameEntity> world) {
        world.get(ScriptComponent.class).forEach((s) -> s.getScripts().forEach((sc) -> sc.update(dt, s.getEntity())));
    }
}
