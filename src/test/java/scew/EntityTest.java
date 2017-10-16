package scew;

import core.entity.GameEntity;
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
        testEntity = new Entity(){};
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

    @Test
    /**
     * Testing adding duplicate components
     */
    public void addComponent1() throws Exception {
        TestComp1 a = new TestComp1(testEntity);
        TestComp1 b = new TestComp1(testEntity);
        testEntity.addComponent(a);
        try{
            testEntity.addComponent(b);
            fail("Shouldn't be able to add a duplicate component.");
        }catch (Entity.DuplicateComponentException e){

        }
    }


    @Test
    /**
     * Testing components can be added and removed correctly.
     */
    public void removeComponent() throws Exception {
        TestComp1 a = new TestComp1(testEntity);
        TestComp2 b = new TestComp2(testEntity);
        testEntity.addComponent(a);
        testEntity.addComponent(b);

        Collection<Component> comps = testEntity.getComponents();
        assertTrue("GameEntity should contain a TestComp1 component.",comps.contains(a));
        assertTrue("GameEntity should contain a TestComp2 component.",comps.contains(b));

        testEntity.removeComponent(a);
        testEntity.removeComponent(TestComp2.class);
        comps = testEntity.getComponents();
        assertFalse("GameEntity should contain a TestComp1 component.",comps.contains(a));
        assertFalse("GameEntity should contain a TestComp2 component.",comps.contains(b));
    }


    @Test
    /**
     * Testing get components
     */
    public void getComponent() throws Exception {
        TestComp1 a = new TestComp1(testEntity);
        TestComp2 b = new TestComp2(testEntity);
        testEntity.addComponent(a);
        testEntity.addComponent(b);

        assertTrue("GameEntity should contain a TestComp1 component.",testEntity.get(TestComp1.class).get().equals(a));
        assertTrue("GameEntity should contain a TestComp2 component.",testEntity.get(TestComp2.class).get().equals(b));
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
}