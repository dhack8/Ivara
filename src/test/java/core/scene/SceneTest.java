package core.scene;

import core.entity.GameEntity;
import core.struct.Camera;
import ivara.entities.PlayerEntity;
import maths.Vector;
import org.junit.Before;
import org.junit.Test;
import scew.Entity;

import java.util.ArrayList;
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
        GameEntity e = new GameEntity(new Vector(1, 1)) {};
        String n = "Test";
        testScene.addEntity(e, Optional.of(n)); //adding a named entity
        assertTrue("Scene should contain the entity.", testScene.getEntities().contains(e));
        assertTrue("Scene should contain only one entity.", testScene.getEntities().size() == 1);
        assertTrue("Scene should contain the entity in the named entities collection.", testScene.getEntity(n).equals(e));
    }//

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

    private class TestScene extends Scene{
        @Override
        public void startScene() {

        }

        @Override
        public Scene hardReset() {
            return null;
        }
    }

}