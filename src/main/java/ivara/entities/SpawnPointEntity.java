package ivara.entities;

import core.components.RenderComponent;
import core.components.SpriteComponent;
import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import maths.Vector;

import java.util.Arrays;

public class SpawnPointEntity extends GameEntity {
    // Constants
    private static final float WIDTH = 1f;
    private static final float HEIGHT = 1f;
    private final static int ANIMATION_RATE = 1000;
    private final float Y_OFFSET = 0.5f;

    public SpawnPointEntity(float x, float y) {
        super(new Vector(x,y));

        // Sprites
        SpriteComponent sc = new SpriteComponent(this);
        sc.add(new FlagSprite(new Vector(WIDTH, HEIGHT), ANIMATION_RATE));
        addComponent(sc);

        // Layer
        addComponent(new RenderComponent(this, 9999999));
    }

    /**
     * This class handles the Sprite relating to the end level entity.
     * @author David Hack
     */
    private class FlagSprite extends AnimatedSprite {
        /**
         * Constructs a flag sprite that changes it's appearance based upon the current state.
         * @param dimensions The size of the sprite.
         * @param frameTick The update tick delay.
         */
        private FlagSprite(Vector dimensions, int frameTick){
            super(new Vector(0,Y_OFFSET), dimensions, frameTick);

            String state = "normal";
            String[] resources = new String[]{
                    "flag-green",
                    "flag-green2"
            };
            addResources(state, Arrays.asList(resources));

            setState("normal");
        }
    }
}
