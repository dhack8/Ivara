package core.entity;

import core.components.Component;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Basic tests for testing the EntityContainer functionality
 * @author Alex Mitchell
 */
public class GameEntityContainerTest {
    private EntityContainer ec;

    @Before
    public void setUp() throws Exception {
        ec = new EntityContainer();
    }

    @Test
    /**
     * Tests that an entity and all its relevant components are stored as they should
     */
    public void testAddEntity() throws Exception {
        GameEntity e = new GameEntity(new Vector(1,1)) {};
        TestComp1 t1 = new TestComp1(e);
        e.addComponent(t1);
        e.addComponent(new TestComp2(e));

        ec.addEntity(e);

        assertTrue("The EntityContainer should contain the added entity", ec.entities.contains(e));
        assertTrue("The EntityContainer should contain a single TestComp1 component", ec.classMap.get(TestComp1.class).size() == 1);
        assertTrue("The EntityContainer should contain a single TestComp2 component", ec.classMap.get(TestComp2.class).size() == 1);
    }//


    // Test component class used to test the Scene functionality
    class TestComp1 extends Component {
        TestComp1(GameEntity e){
            super(e);
        }
    }

    // Test component class used to test the Scene functionality
    class TestComp2 extends Component{
        TestComp2(GameEntity e){
            super(e);
        }
    }

}