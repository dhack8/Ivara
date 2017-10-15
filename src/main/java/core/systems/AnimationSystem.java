package core.systems;

import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import scew.System;
import scew.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Animation system class that retrieves an array of SpriteComponents of the world (in the form of game entities), and
 * adds them to a list of Animated Sprites. Each sprite is then given their updated resource so the animation is able
 * to be performed.
 * @author Will Pearson
 */

public class AnimationSystem extends System<GameEntity> {

    /**
     * Update tick as outlined above.
     * @param dt time since last tick.
     * @param world the game world.
     */
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

