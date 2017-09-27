package core.entity;

import core.components.Component;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Testing the basic actions of entities
 * @author Alex Mitchell
 */
public class EntityTest {
    private Entity testEntity;

    @Before
    public void setUp() throws Exception {
        testEntity = new Entity(new Vector(1,1)) {};
    }

    @Test
    /**
     * Tests basic translations with dx and dy
     */
    public void testTranslate() throws Exception {
        Vector initial = new Vector(testEntity.getPosition());
        float dx = 10;
        float dy = 15;

        testEntity.translate(dx,dy);
        assertTrue("Entity should be at x position " + initial.x + dx + " but was " + testEntity.getPosition().x, initial.x + dx == testEntity.getPosition().x);
        assertTrue("Entity should be at y position " + initial.y + dy + " but was " + testEntity.getPosition().y, initial.y + dy == testEntity.getPosition().y);

        testEntity.translate(-dx,-dy);
        assertTrue("Entity should be at x position " + initial.x + " but was " + testEntity.getPosition().x, initial.x == testEntity.getPosition().x);
        assertTrue("Entity should be at y position " + initial.y + " but was " + testEntity.getPosition().y, initial.y == testEntity.getPosition().y);
    }

    @Test
    /**
     * Tests basic translations with a translation vector
     */
    public void testTranslate1() throws Exception {
        Vector initial = new Vector(testEntity.getPosition());
        Vector translate = new Vector(10,15);

        testEntity.translate(translate);
        assertTrue("Entity should be at x position " + initial.x + translate.x + " but was " + testEntity.getPosition().x, initial.x + translate.x == testEntity.getPosition().x);
        assertTrue("Entity should be at y position " + initial.y + translate.y + " but was " + testEntity.getPosition().y, initial.y + translate.y == testEntity.getPosition().y);

        testEntity.translate(translate.mult(-1));
        assertTrue("Entity should be at x position " + initial.x + " but was " + testEntity.getPosition().x, initial.x == testEntity.getPosition().x);
        assertTrue("Entity should be at y position " + initial.y + " but was " + testEntity.getPosition().y, initial.y == testEntity.getPosition().y);
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
        assertTrue("Entity should contain a TestComp1 component.",comps.contains(a));
        assertTrue("Entity should contain a TestComp2 component.",comps.contains(b));
    }

    class TestComp1 extends Component{
        public TestComp1(Entity e){
            super(e);
        }
    }

    class TestComp2 extends Component{
        public TestComp2(Entity e){
            super(e);
        }
    }

    //Todo add more component checks

}