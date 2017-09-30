package core.systems;

import core.components.AnimatedSpriteComponent;
import core.components.SpriteComponent;
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
        AnimatedSpriteComponent[] animators = world.get(AnimatedSpriteComponent.class).toArray(new AnimatedSpriteComponent[0]);
        for (AnimatedSpriteComponent anime : animators) {
            String resourceID = anime.getCurrentResource(dt);
            anime.getEntity().get(SpriteComponent.class).setResourceID(resourceID);
        }
    }
}

