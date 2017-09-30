package core.systems;

import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import scew.System;
import scew.World;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: javadoc
 * @author Will Pearson
 */

public class AnimationSystem extends System<GameEntity> {


    @Override
    public void update(int dt, World<GameEntity> world) {
        SpriteComponent[] scs = world.get(SpriteComponent.class).toArray(new SpriteComponent[0]);
        List<AnimatedSprite> animatedSprites = new ArrayList<>();
        for (int i = 0; i < scs.length; i++) {
            animatedSprites.addAll(scs[i].getAnimatedSprites());
        }
        for (AnimatedSprite anime : animatedSprites) {
            anime.updateResource(dt);
        }
    }
}

