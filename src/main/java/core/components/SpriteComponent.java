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
 * Sprite component holds sprites and is the only sprite component to the parent entity
 * @author David Hack
 */
public class SpriteComponent extends Component<GameEntity> {

    //Basic non animated sprites
    private final List<Sprite> sprites = new ArrayList<>();

    //Animated sprites
    private final List<AnimatedSprite> animatedSprites = new ArrayList<>();

    //Constructors --------------------------------------------

    /**
     * Constructor for a sprite component that has no sprites.
     * @param entity parent entity
     */
    public SpriteComponent(GameEntity entity) {
        super(entity);
    }

    /**
     * Constructor for a sprite component that takes one animated sprite.
     * @param entity parent entity
     * @param animatedSprite animated sprite to add
     */
    public SpriteComponent(GameEntity entity, AnimatedSprite animatedSprite) {
        super(entity);
        sprites.add(animatedSprite);
        animatedSprites.add(animatedSprite);
    }

    /**
     * Constructor for a sprite component that takes one sprite.
     * @param entity parent entity
     * @param sprite sprite to add
     */
    public SpriteComponent(GameEntity entity, Sprite sprite) {
        super(entity);
        sprites.add(sprite);
    }

    //End of constructors----------------------------------------

    /**
     * Adds a sprite to the component.
     * @param s sprite to add
     */
    public void add(Sprite s){
        sprites.add(s);
    }

    /**
     * Adds an animated sprite to the component.
     * @param as animated sprite to add
     */
    public void add(AnimatedSprite as) {
        sprites.add(as);
        animatedSprites.add(as);
    }

    /**
     * Adds a new sprite from required parameters
     * @param id resourceID object
     * @param transform transform vector
     * @param dimension dimensions vector
     */
    public void add(ResourceID id, Vector transform, Vector dimension){
        sprites.add(new Sprite(id, transform, dimension));
    }

    /**
     * Adds a new sprite with no transform from the parent entity
     * @param id resourceID object
     * @param dimensions dimensions vector
     */
    public void add(ResourceID id, Vector dimensions){
        sprites.add(new Sprite(id, new Vector(0,0), dimensions));
    }

    /**
     * Getter for the basic sprites
     * @return list of basic sprites
     */
    public List<Sprite> getSprites(){
        return sprites;
    }

    /**
     * Getter for the animatedSprites
     * @return list of animated sprites
     */
    public List<AnimatedSprite> getAnimatedSprites() {
        return animatedSprites;
    }
}
