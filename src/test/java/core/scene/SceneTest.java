package core.scene;

import core.components.Component;
import core.entity.Entity;
import maths.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

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
        testScene = new Scene() {}; // Creating a general scene for testing purposes
    }

    @Test
    /**
     * Will test a simple add of a new unnamed entity and whether the entity exists in the scene
     */
    public void testAddEntity() throws Exception {
        Entity e = new Entity(new Vector(1, 1)) {};
        testScene.addEntity(e); // adding an unnamed entity
        assertTrue("Scene should contain the entity.", testScene.getEntities().contains(e));
        assertTrue("Scene should contain only one entity.", testScene.getEntities().size() == 1);
    }

    @Test
    /**
     * Tests that a named entity is added to the scene correctly
     */
    public void testAddEntity1() throws Exception {
        Entity e = new Entity(new Vector(1, 1)) {};
        String n = "Test";
        testScene.addEntity(e, n); //adding a named entity
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
            Entity e = new Entity(new Vector(1, 1)){}; // irrelevant position
            if(i % 2 != 0) testScene.addEntity(e);
            else testScene.addEntity(e, name + i);
        }

        assertTrue("Scene should contain " + numEntities + " entities, but found " + testScene.getEntities().size() + ".", testScene.getEntities().size() == numEntities);

        for(int i = 2; i <= numEntities; i+=2){
            assertTrue("Scene should contain the named entity " + name + i + ".", testScene.getEntity(name + i) != null);
        }
    }


    @Test
    public void update() throws Exception { // Todo how should I test this

    }


}