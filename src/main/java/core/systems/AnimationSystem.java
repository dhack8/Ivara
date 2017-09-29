package core.systems;

import core.entity.GameEntity;
import eem.System;
import eem.World;

/**
 * TODO: javadoc
 * @author Will Pearson
 */
public class AnimationSystem extends System<GameEntity> {


    @Override
    public void update(int dt, World<GameEntity> world) {
        //getEntities().getAllComponents(AnimatedSpriteComponent.class)
        //for (GameEntity entity : getEntities()) {
            //String resourceID = entity.getComponents(AnimatedSpriteComponent.class).stream().findAny().get().getCurrentResource(delta);
            //entity.getComponents(SpriteComponent.class).stream().findAny().get().setResourceID(resourceID);
    }
}

