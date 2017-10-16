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
 * Testing class for the Scene
 *
 * @author Alex Mitchell
 */
public class SceneTest {
    private Scene testScene;

    @Before
    public void setUp() throws Exception {
        testScene = new TestScene(); // Creating a general scene for testing purposes
    }

    @Test
    /**
     * Will test a simple add of a new unnamed entity and whether the entity exists in the scene
     */
    public void testAddEntity() throws Exception {
        GameEntity e = new GameEntity(new Vector(1, 1)) {};
        testScene.addEntity(e); // adding an unnamed entity
        assertTrue("Scene should contain the entity.", testScene.getEntities().contains(e));
        assertTrue("Scene should contain only one entity.", testScene.getEntities().size() == 1);
    }

    @Test
    /**
     * Tests that a named entity is added to the scene correctly
     */
    public void testAddEntity1() throws Exception {
        GameEntity e1 = new GameEntity(new Vector(1, 1)){};
        GameEntity e2 = new GameEntity(new Vector(1, 1)){};

        String n = "Test";
        testScene.addEntity(e1, Optional.of(n)); //adding a named entity
        assertTrue("Scene should contain the entity.", testScene.getEntities().contains(e1));
        assertTrue("Scene should contain only one entity.", testScene.getEntities().size() == 1);
        assertTrue("Scene should contain the entity in the named entities collection.", testScene.getEntity(n).equals(e1));

        testScene.addEntity(e2, Optional.empty()); //adding an unnamed entity
        assertTrue("Scene should contain the entity.", testScene.getEntities().contains(e2));
        assertTrue("Scene should contain two entities.", testScene.getEntities().size() == 2);
    }

    @Test
    /**
     * Tests adding a variety of entities
     */
    public void testAddEntity2() throws Exception {
        String name = "Test";
        int numEntities = 10;
        for (int i = 1; i <= numEntities; i++) {
            // alternating between adding a named and unnamed entity
            GameEntity e = new GameEntity(new Vector(1, 1)){}; // irrelevant position
            if(i % 2 != 0) testScene.addEntity(e);
            else testScene.addEntity(e, Optional.of(name + i));
        }

        assertTrue("Scene should contain " + numEntities + " entities, but found " + testScene.getEntities().size() + ".", testScene.getEntities().size() == numEntities);

        for(int i = 2; i <= numEntities; i+=2){
            assertTrue("Scene should contain the named entity " + name + i + ".", testScene.getEntity(name + i) != null);
        }
    }

    @Test
    /**
     * Tests adding a collection of entities
     */
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

    @Test
    /**
     * Tests getting an entity of a given class
     */
    public void testGetEntity() throws Exception{
        TestEntity e = new TestEntity(new Vector(1,1));
        assertTrue("There should have been no TestEntities.",testScene.getEntity(TestEntity.class) == null);
        testScene.addEntity(e);
        assertTrue("There should have been a TestEntity.",testScene.getEntity(TestEntity.class) != null);
        assertTrue("Entity should equal tested entity.",testScene.getEntity(TestEntity.class).equals(e));
        assertTrue("There should have been no TestEntity2's.",testScene.getEntity(TestEntity2.class) == null);
    }


    @Test
    /**
     * Tests getting a collection of entities of a given class
     */
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


    @Test
    /**
     * Testing the camera creation
     */
    public void testCamera() throws Exception{

        assertTrue("Camera should be null.",testScene.getCamera()==null);
        testScene.setCamera(new Camera(new Vector(0,0), new Vector(16,9)));
        assertTrue("Should've found a camera", testScene.getCamera()!=null);
    }

    @Test
    /**
     * Test removal of an entity
     */
    public void testRemove(){
        GameEntity e = new GameEntity(new Vector(0,0)) {};
        testScene.addEntity(e);
        testScene.removeEntity(e);
        assertTrue("The entity should not exist.", !testScene.getEntities().contains(e));
    }

    @Test
    /**
     * Test removal of an entity that doesn't exist
     */
    public void testRemove1(){
        GameEntity e = new GameEntity(new Vector(0,0)) {};
        testScene.removeEntity(e); // no error thrown
    }


    @Test
    /**
     * Testing the reset of a scene.
     */
    public void testReset(){
        String testName = "test";
        TestEntity e = new TestEntity(new Vector(1,1));
        testScene.addEntity(e, Optional.of(testName));
        testScene.resetScene();

        assertTrue("The entities collection should be empty, but wasn't.",testScene.getEntities().size() == 0);
        assertTrue("Found the named entity even though it shouldn't exist in the scene.",testScene.getEntity(testName) == null);
    }


    private class TestScene extends Scene{
        @Override
        public void startScene() {

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