package ivara.entities;

import core.components.RenderComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.ResourceID;
import core.struct.Sprite;
import maths.Vector;

/**
 * This class handles the background entity.
 * @author James Magallanes
 */
public class BackgroundEntity extends GameEntity {

    /**
     * Creates a background entity at the specified location, on the -1 layer (behind all entities in the foreground).
     * @param x x location
     * @param y y location
     */
    public BackgroundEntity(float x, float y){
        super(new Vector(x,y));

        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new Sprite(new ResourceID("background"), new Vector(0f, 0f), null));

        addComponent(sc);

        addComponent(new RenderComponent(this, -1, RenderComponent.Mode.FULLSCREEN));
    }
}
