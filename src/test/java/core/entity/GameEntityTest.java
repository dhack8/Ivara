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