package core.entity;

import core.scene.Scene;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This testing class expands on the testing of the EntityTest class.
 * The expansion relates to the additional features that the GameEntity has.
 * @author Alex Mitchell
 */
public class GameEntityTest {

    private GameEntity testEntity;

    @Before
    public void init(){
        testEntity = new TestEntity(new Vector(1,1));
    }

    /**
     * Testing allocation of scenes
     */
    @Test
    public void testScene1(){
        Scene test = new TestScene();
        testEntity.setScene(test);
        assertTrue("Should be able to get the scene, but couldn't.", testEntity.getScene().equals(test));
    }

    private class TestEntity extends GameEntity{
        public TestEntity(Vector transform) {
            super(transform);
        }
    }

    private class TestScene extends Scene{
        @Override
        public void initialize() {

        }
    }

}