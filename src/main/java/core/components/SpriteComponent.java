package core.components;

import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import core.struct.ResourceID;
import core.struct.Sprite;
import scew.Component;
import maths.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Callum Li on 9/15/17.
 */
public class SpriteComponent extends Component<GameEntity> {


    private final List<Sprite> sprites = new ArrayList<>();
    private final List<AnimatedSprite> animatedSprites = new ArrayList<>();

    /**
     * Constructor for a sprite component that has a width and height along with a resource identifier.
     * This sprite has a dimension.
     * @param entity parent entity
     */
    public SpriteComponent(GameEntity entity) {
        super(entity);
    }

    /**
     * Adds a sprite to the component.
     * @param s sprite to add
     */
    public void add(Sprite s){
        sprites.add(s);
    }

    public void add(ResourceID id, Vector transform, Vector dimension){
        sprites.add(new Sprite(id, transform, dimension));
    }

    public void add(ResourceID id, Vector dimensions){
        sprites.add(new Sprite(id, new Vector(0,0), dimensions));
    }

    public List<Sprite> getSprites(){
        return sprites;
    }

    public List<AnimatedSprite> getAnimatedSprites() {
        return animatedSprites;
    }
}
