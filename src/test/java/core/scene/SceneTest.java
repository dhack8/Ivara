package core.scene;

import core.Game;
import core.entity.GameEntity;
import core.struct.Camera;
import ivara.entities.PlayerEntity;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;
import scew.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * This class tests the functionality of the Scene class.
 * @author Alex Mitchell
 */
public class SceneTest {
    private Scene testScene;

    @Before
    public void setUp() throws Exception {
        testScene = new TestScene(); // Creating a general scene for testing purposes
    }

    /**
     * Will test a simple add of a new unnamed entity and whether the entity exists in the scene
     */
    @Test
    public void testAddEntity() throws Exception {
        GameEntity e = new GameEntity(new Vector(1, 1)) {};
        testScene.addEntity(e); // adding an unnamed entity
        assertTrue("Scene should contain the entity.", testScene.getEntities().contains(e));
        assertTrue("Scene should contain only one entity.", testScene.getEntities().size() == 1);
    }

    /**
     * Tests adding a collection of entities
     */
    @Test
    public void testAddEntity3() throws Exception {
        int numEntities = 10;
        List<GameEntity> entities = new ArrayList<>();
        for(int i = 0; i < numEntities; i++){
            entities.add(new GameEntity(new Vector(0,0)){});
        }
        testScene.addEntities(entities);
        for (GameEntity e : entities) {
            assertTrue("Entity does not exist in the scene.", testScene.getEntities().contains(e));
        }
    }


    /**
     * Tests getting an entity of a given class
     */
    @Test
    public void testGetEntity() throws Exception{
        TestEntity e = new TestEntity(new Vector(1,1));
        assertTrue("There should have been no TestEntities.",testScene.getEntity(TestEntity.class) == null);
        testScene.addEntity(e);
        assertTrue("There should have been a TestEntity.",testScene.getEntity(TestEntity.class) != null);
        assertTrue("Entity should equal tested entity.",testScene.getEntity(TestEntity.class).equals(e));
        assertTrue("There should have been no TestEntity2's.",testScene.getEntity(TestEntity2.class) == null);
    }

    /**
     * Tests getting a collection of entities of a given class
     */
    @Test
    public void testGetEntities() throws Exception{
        TestEntity e1 = new TestEntity(new Vector(0,0));
        testScene.addEntity(e1);
        TestEntity e2 = new TestEntity(new Vector(0,0));
        testScene.addEntity(e2);
        TestEntity2 e3 = new TestEntity2(new Vector(0,0));
        testScene.addEntity(e3);
        TestEntity2 e4 = new TestEntity2(new Vector(0,0));
        testScene.addEntity(e4);
        TestEntity2 e5 = new TestEntity2(new Vector(0,0));
        testScene.addEntity(e5);

        Collection<GameEntity> testEntities = testScene.getEntities(TestEntity.class);
        assertTrue("There should be 2 TestEntity objects.", testEntities.size() == 2);
        assertTrue("Collection did not contain the given element.", testEntities.contains(e1));
        assertTrue("Collection did not contain the given element.", testEntities.contains(e2));

        Collection<GameEntity> testEntities2 = testScene.getEntities(TestEntity2.class);
        assertTrue("There should be 2 TestEntity objects.", testEntities2.size() == 3);
        assertTrue("Collection did not contain the given element.", testEntities2.contains(e3));
        assertTrue("Collection did not contain the given element.", testEntities2.contains(e4));
        assertTrue("Collection did not contain the given element.", testEntities2.contains(e5));

    }

    /**
     * Testing the camera creation
     */
    @Test
    public void testCamera() throws Exception{

        assertTrue("Camera should be null.",testScene.getCamera()==null);
        testScene.setCamera(new Camera(new Vector(0,0), new Vector(16,9)));
        assertTrue("Should've found a camera", testScene.getCamera()!=null);
    }

    /**
     * Test removal of an entity
     */
    @Test
    public void testRemove(){
        GameEntity e = new GameEntity(new Vector(0,0)) {};
        testScene.addEntity(e);
        testScene.removeEntity(e);
        assertTrue("The entity should not exist.", !testScene.getEntities().contains(e));
    }

    /**
     * Test removal of an entity that doesn't exist
     */
    @Test
    public void testRemove1(){
        GameEntity e = new GameEntity(new Vector(0,0)) {};
        testScene.removeEntity(e); // no error thrown
    }

    private class TestScene extends Scene{
        @Override
        public void initialize() {

        }
    }

    private class TestEntity extends GameEntity{
        public TestEntity(Vector transform) {
            super(transform);
        }
    }
    private class TestEntity2 extends GameEntity{
        public TestEntity2(Vector transform) {
            super(transform);
        }
    }

}