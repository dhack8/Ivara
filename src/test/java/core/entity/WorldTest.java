package core.entity;

import maths.Vector;
import org.junit.Before;
import org.junit.Test;
import scew.Component;
import scew.World;

import static org.junit.Assert.*;

/**
 * Basic tests for testing the EntityContainer functionality
 * @author Alex Mitchell
 */
public class WorldTest {
    private World w;

    @Before
    public void setUp() throws Exception {
        w = new World();
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

        w.addEntity(e);

        assertTrue("The EntityContainer should contain the added entity", w.getEntities().contains(e));
        assertTrue("The EntityContainer should contain a single TestComp1 component", w.get(TestComp1.class).size() == 1);
        assertTrue("The EntityContainer should contain a single TestComp2 component", w.get(TestComp2.class).size() == 1);
    }//


    // Test component class used to test the Scene functionality
    class TestComp1 extends Component<GameEntity> {
        TestComp1(GameEntity e){
            super(e);
        }
    }

    // Test component class used to test the Scene functionality
    class TestComp2 extends Component<GameEntity>{
        TestComp2(GameEntity e){
            super(e);
        }
    }

}