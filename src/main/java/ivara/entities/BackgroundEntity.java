package ivara.entities;

import core.components.LayerComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
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
        addComponent(new SpriteComponent(this, "background"));
        addComponent(new LayerComponent(this, -1));
    }
}
