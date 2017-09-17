package physics;

import core.components.AnimatedSpriteComponent;
import core.components.PSpriteComponent;
import core.entity.Entity;
import core.entity.EntityContainer;

/**
 * TODO: javadoc
 * @author Will Pearson
 */
public class AnimationSystem extends EntitySystem {

    public AnimationSystem(EntityContainer entities) {
        super(entities);
    }

    @Override
    public void update(long delta) {
        for (Entity entity : getEntities()) {
            String resourceID = entity.getComponents(AnimatedSpriteComponent.class).stream().findAny().get().getCurrentResource(delta);
            entity.getComponents(PSpriteComponent.class).stream().findAny().get().setResourceID(resourceID);
        }
    }
}
