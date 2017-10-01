package core.components;

import core.entity.GameEntity;
import core.struct.AnimatedSprite;
import core.struct.ResourceID;
import core.struct.Sprite;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the sprite component
 * @author David Hack
 */
public class SpriteComponentTest {

    GameEntity testEntity;

    /**
     * Sets up game entity.
     */
    @Before
    public void setup() {
        testEntity = new GameEntity(new Vector(0,0)){};
    }

    /**
     * Test the single sprite constructor.
     * @throws Exception
     */
    @Test
    public void singleBasicConstruct() throws Exception{
        Sprite testSprite = new Sprite(new ResourceID("player.png"), new Vector(0,0), new Vector(1,1));
        SpriteComponent sc = new SpriteComponent(testEntity, testSprite);
        assertEquals(testSprite, sc.getSprites().get(0));
        assertTrue(sc.getAnimatedSprites().isEmpty());
    }

    /**
     * Test the single animated sprite constructor.
     * @throws Exception
     */
    @Test
    public void singleAnimatedConstruct() throws Exception{
        AnimatedSprite testSprite = new AnimatedSprite(new Vector(0,0), new Vector(1,1), 1);
        SpriteComponent sc = new SpriteComponent(testEntity, testSprite);
        assertEquals(testSprite, sc.getAnimatedSprites().get(0));
        assertTrue(sc.getSprites().isEmpty());
    }

    @Test
    public void add() throws Exception {
        Sprite testSprite = new Sprite(new ResourceID("player.png"), new Vector(0,0), new Vector(1,1));
        SpriteComponent sc = new SpriteComponent(testEntity);

        sc.add(testSprite);

        assertEquals(testSprite, sc.getSprites().get(0));
        assertTrue(sc.getAnimatedSprites().isEmpty());
    }

    @Test
    public void add1() throws Exception {
        SpriteComponent sc = new SpriteComponent(testEntity);

        sc.add(new ResourceID("player.png"), new Vector(0,0), new Vector(1,1));

        assertEquals(1, sc.getSprites().size());
        assertFalse(sc.getSprites().isEmpty());
        assertTrue(sc.getAnimatedSprites().isEmpty());
    }

    @Test
    public void add2() throws Exception {
        SpriteComponent sc = new SpriteComponent(testEntity);

        sc.add(new ResourceID("player.png"), new Vector(1,1));

        assertEquals(1, sc.getSprites().size());
        assertFalse(sc.getSprites().isEmpty());
        assertTrue(sc.getAnimatedSprites().isEmpty());
    }

    @Test
    public void add3() throws Exception {
        AnimatedSprite testSprite = new AnimatedSprite(new Vector(0,0), new Vector(1,1), 1);
        SpriteComponent sc = new SpriteComponent(testEntity);

        sc.add(testSprite);

        assertEquals(testSprite, sc.getAnimatedSprites().get(0));
        assertTrue(sc.getSprites().isEmpty());
    }
}