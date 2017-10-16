package core.components;

import core.entity.GameEntity;
import core.struct.Text;
import maths.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for the TextComponent
 * @author James Magallanes
 */
public class TextComponentTest {

    GameEntity testEntity;

    /**
     * Set up the test GameEntity
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testEntity = new GameEntity(new Vector(0,0)){};
    }

    /**
     * Tests the construction of a TextComponent.
     * @throws Exception
     */
    @Test
    public void basicConstruct() throws Exception{
        Text testText = new Text(new Vector(0,0), 20f, "test");
        TextComponent tc = new TextComponent(testEntity, testText);
        assertEquals(testText, tc.getTexts().get(0));
    }

    /**
     * Tests the addition of a Text object into the TextComponent's list of Texts.
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        Text testText = new Text(new Vector(0,0), 20f, "test");
        TextComponent tc = new TextComponent(testEntity);
        tc.add(testText);
        assertEquals(testText, tc.getTexts().get(0));
    }

    /**
     * Tests the addition of a string of text and a font size (represented as a float) into a TextComponent.
     * @throws Exception
     */
    @Test
    public void add1() throws Exception {
        TextComponent tc = new TextComponent(testEntity);
        tc.add("test", 20f);
        assertEquals(tc.getTexts().size(), 1);
    }

    /**
     * Tests the addition of a vector, string of text, and a font size (represented as a float) into a TextComponent.
     * @throws Exception
     */
    @Test
    public void add2() throws Exception {
        TextComponent tc = new TextComponent(testEntity);
        tc.add(new Vector(0,0), "test", 20f);
        assertEquals(tc.getTexts().size(), 1);
        assertEquals(tc.getTexts().get(0).fontSize, 20, 0.001);
        assertEquals(tc.getTexts().get(0).transform, new Vector(0,0));
        assertEquals(tc.getTexts().get(0).text, "test");
    }

    /**
     * Tests that clearing the text component works.
     * @throws Exception
     */
    @Test
    public void clear() throws Exception {
        TextComponent tc = new TextComponent(testEntity);
        tc.add(new Vector(0,0), "test", 20f);
        assertEquals(tc.getTexts().size(), 1);
        tc.clear();
        assertEquals(tc.getTexts().size(), 0);
    }
}