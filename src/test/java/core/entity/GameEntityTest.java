package core.entity;

import scew.Component;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Testing the basic actions of entities
 * @author Alex Mitchell
 */
public class GameEntityTest {
    private GameEntity testEntity;

    @Before
    public void setUp() throws Exception {
        testEntity = new GameEntity(new Vector(1,1)) {};
    }

    @Test
    /**
     * Tests basic translations with dx and dy
     */
    public void testTranslate() throws Exception {
        Vector initial = new Vector(testEntity.getTransform());
        float dx = 10;
        float dy = 15;

        testEntity.translate(dx,dy);
        assertTrue("GameEntity should be at x position " + initial.x + dx + " but was " + testEntity.getTransform().x, initial.x + dx == testEntity.getTransform().x);
        assertTrue("GameEntity should be at y position " + initial.y + dy + " but was " + testEntity.getTransform().y, initial.y + dy == testEntity.getTransform().y);

        testEntity.translate(-dx,-dy);
        assertTrue("GameEntity should be at x position " + initial.x + " but was " + testEntity.getTransform().x, initial.x == testEntity.getTransform().x);
        assertTrue("GameEntity should be at y position " + initial.y + " but was " + testEntity.getTransform().y, initial.y == testEntity.getTransform().y);
    }

    @Test
    /**
     * Tests basic translations with a translation vector
     */
    public void testTranslate1() throws Exception {
        Vector initial = new Vector(testEntity.getTransform());
        Vector translate = new Vector(10,15);

        testEntity.translate(translate);
        assertTrue("GameEntity should be at x position " + initial.x + translate.x + " but was " + testEntity.getTransform().x, initial.x + translate.x == testEntity.getTransform().x);
        assertTrue("GameEntity should be at y position " + initial.y + translate.y + " but was " + testEntity.getTransform().y, initial.y + translate.y == testEntity.getTransform().y);

        testEntity.translate(translate.mult(-1));
        assertTrue("GameEntity should be at x position " + initial.x + " but was " + testEntity.getTransform().x, initial.x == testEntity.getTransform().x);
        assertTrue("GameEntity should be at y position " + initial.y + " but was " + testEntity.getTransform().y, initial.y == testEntity.getTransform().y);
    }

    @Test
    /**
     * Testing components can be added and gathered
     */
    public void addComponent() throws Exception {
        TestComp1 a = new TestComp1(testEntity);
        TestComp2 b = new TestComp2(testEntity);
        testEntity.addComponent(a);
        testEntity.addComponent(b);

        Collection<Component> comps = testEntity.getComponents();
        assertTrue("GameEntity should contain a TestComp1 component.",comps.contains(a));
        assertTrue("GameEntity should contain a TestComp2 component.",comps.contains(b));
    }

    class TestComp1 extends Component{
        public TestComp1(GameEntity e){
            super(e);
        }
    }

    class TestComp2 extends Component{
        public TestComp2(GameEntity e){
            super(e);
        }
    }

    //Todo add more component checks

}