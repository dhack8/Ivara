package ivara.entities;

import core.components.*;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import maths.Vector;
import physics.AABBCollider;

/**
 * This class handles the background entity.
 * @author James Magallanes
 */
public class BackgroundEntity extends GameEntity {

    /**
     * Creates a background entity that consists of a sprite.
     */
    public BackgroundEntity(ResourceID id){
        super(new Vector(0,0));

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(id, new Vector(0f, 0f), null));
        addComponent(sc);

        addComponent(new RenderComponent(this, -1, RenderComponent.Mode.FULLSCREEN));
    }
}
