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
 * @author Alex Mitchell
 */
public class SceneTest {
    Scene testScene;

    @Before
    public void setUp() throws Exception {
        testScene = new Scene(){}; // Creating a general scene for testing purposes
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetEntities() throws Exception {
        // won't need to test
    }

    @Test
    /**
     * Will test a simple add of a new unnamed entity and whether the entity exists in the scene
     */
    public void testAddEntity() throws Exception {
        Entity e = new Entity(new Vector(1,1)){};
        testScene.addEntity(e); // adding an unnamed entity
        assertTrue("Scene should contain the entity.", testScene.getEntities().contains(e));
        assertTrue("Scene should contain only one entity.", testScene.getEntities().size() == 1);
    }

    @Test
    /**
     * Tests that a named entity is added to the scene correctly
     */
    public void testAddEntity1() throws Exception {
        Entity e = new Entity(new Vector(1,1)){};
        String n = "Test";
        testScene.addEntity(e, n); //adding a named entity
        assertTrue("Scene should contain the entity.", testScene.getEntities().contains(e));
        assertTrue("Scene should contain only one entity.", testScene.getEntities().size() == 1);
        assertTrue("Scene should contain the entity in the named entities collection.", testScene.getEntity(n).equals(e));
    }

    @Test
    public void getEntity() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getCamera() throws Exception {

    }

    @Test
    public void isDrawing() throws Exception {

    }

    @Test
    public void setDrawing() throws Exception {

    }

}