package core.scene;

import backends.InputBroadcaster;
import backends.Renderer;
import backends.processing.PWindow;
import core.Game;
import core.input.KeyListener;
import core.input.MouseListener;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alex Mitchell
 */
public class LevelManagerTest {
    private LevelManager lm;

    /**
     * Sets up a scene and returns the levels used in the scene for comparison
     * @param numLevels The number of levels to create
     * @return A copy of the scenes used in the LevelManager
     */
    private List<Scene> setUpScenes(int numLevels){
        List<Scene> levels = new ArrayList<>();
        for(int i = 0; i < numLevels; i++){
            levels.add(new TestScene());
        }
        lm = new LevelManager(levels);
        return new ArrayList<>(levels);
    }

    @Test
    /**
     * Testing that a LevelManager cannot be constructed with a null scene
     */
    public void testConstruction1(){
        Scene s = null;
        try{
            lm = new LevelManager(s);
            fail("Should not have been able to construct a levelmanager with a null scene.");
        }catch(IllegalArgumentException e){

        }
    }

    @Test
    /**
     *  Testing that a LevelManager cannot be constructed with a null collection of scenes
     */
    public void testConstructor2(){
        List<Scene> levels = null;
        try{
            lm = new LevelManager(levels);
            fail("Should not be able to construct a levelmanager with a null collection of scenes.");
        }catch(IllegalArgumentException e){

        }
    }

    @Test
    /**
     * Testing the LevelManager with an empty list of scenes
     */
    public void testConstructor3(){
        List<Scene> levels = new ArrayList<>();
        try{
            lm = new LevelManager(levels);
            fail("Should not be able to construct a levelmanager with an empty collection of scenes.");
        }catch(IllegalArgumentException e){

        }
    }

    @Test
    /**
     * Testing that a LevelManager cannot be have actions called on when there is no reference to a game
     */
    public void testNoGame1(){
        Scene s = new TestScene();
        try{
            lm = new LevelManager(s);
            lm.nextScene();
            fail("Should not have been able to call actions on the LevelManager.");
        }catch(IllegalArgumentException e){

        }
    }

    @Test
    /**
     * Tests whether the pause menu is correctly set in the simple case
     */
    public void testPause1(){
        setUpScenes(1);
        setupGame();
        Scene pauseMenu = new TestScene();
        lm.setPauseMenu(pauseMenu);
        assertTrue("The pause menu did not equal the provided pause menu.", pauseMenu.equals(lm.getPauseMenu()));
        lm.pause();
        assertTrue("The current scene is not the pause scene.", pauseMenu.equals(lm.getCurrentScene()));
    }

    @Test
    /**
     * Tests setting a null pauseMenu
     */
    public void testPause2(){
        setUpScenes(1);
        setupGame();
        Scene pauseMenu = null;
        try{
            lm.setPauseMenu(pauseMenu);
            fail("Should not have been able to set a pause menu.");
        }catch(IllegalArgumentException e){

        }
    }

    @Test
    /**
     * Tests pausing when the menu is not set
     */
    public void testPause3(){
        setUpScenes(1);
        setupGame();
        try{
            lm.pause();
            fail("Should not have been able to pause.");
        }catch(RuntimeException e){

        }
    }

    @Test
    /**
     * Testing to see if the nextScene changes between scenes for the simple case
     */
    public void testNextScene1(){
        
        List<Scene> scenes = setUpScenes(5);
        setupGame();
        for(int i = 0; i < scenes.size(); i++){
            assertTrue("Wrong current scene.",lm.getCurrentScene().equals(scenes.get(i)));
            lm.nextScene();
            assertTrue("Next scene is the wrong scene",(i==scenes.size()-1)?lm.getCurrentScene().equals(scenes.get(0)) : lm.getCurrentScene().equals(scenes.get(i+1)));
        }
    }

    @Test
    /**
     * Testing to see if the nextScene can be called when the pause menu is open
     */
    public void testNextScene2(){
        List<Scene> scenes = setUpScenes(5);
        setupGame();
        lm.setPauseMenu(new TestScene());
        lm.pause();
        try{
            lm.nextScene();
            fail("Should not be able to go to the next scene.");
        }catch (RuntimeException e){

        }
    }

    @Test
    /**
     * Testing adding a single scene
     */
    public void testAddScene1(){
        int initialSize = 1;
        setUpScenes(initialSize);
        setupGame();
        Scene s = new TestScene();
        lm.addScene(s);
        assertTrue("New Scene should be at index: " + initialSize + 1, lm.getScene(initialSize).equals(s));
    }

    @Test
    /**
     * Testing adding a single null scene
     */
    public void testAddScene2(){
        int initialSize = 1;
        setUpScenes(initialSize);
        setupGame();
        try{
            Scene s1 = null;
            lm.addScene(s1);
            fail("Should not be able to add a null scene.");
        }catch(IllegalArgumentException e){

        }
    }

    @Test
    /**
     * Testing adding a scene to a specified position
     */
    public void testAddScene3(){
        int initialSize = 2;
        setUpScenes(initialSize);
        setupGame();
        Scene s = new TestScene();
        Scene s1 = new TestScene();
        Scene s2 = new TestScene();

        lm.addScene(s, 0); // adding to the front
        assertTrue("The scene should be in position 0.", lm.getScene(0).equals(s));
        lm.addScene(s1, 3); // adding to the back
        assertTrue("The scene should be in position 3.", lm.getScene(3).equals(s1));
        lm.addScene(s2, 2); // adding to the middle
        assertTrue("The scene should be in position 3.", lm.getScene(2).equals(s2));
    }

    @Test
    /**
     * Testing adding a scene to an invalid position
     */
    public void testAddScene4(){
        int initialSize = 2;
        setUpScenes(initialSize);
        setupGame();
        Scene s = new TestScene();
        try{
            lm.addScene(s, -1);
            lm.addScene(s, initialSize + 2);
            fail("Shouldn't be able to add to an invalid position");
        }catch(IllegalArgumentException e){

        }
    }

    @Test
    /**
     * Testing adding a collection of scenes
     */
    public void testAddScene5(){
        int initialSize = 2;
        setUpScenes(initialSize);
        setupGame();
        List<Scene> scenes = new ArrayList<>();
        Scene s = new TestScene();
        scenes.add(s);
        lm.addScenes(scenes);
        assertTrue("The LevelManager should contain the new scene added with a list.", lm.getScene(2).equals(s));
    }

    @Test
    /**
     * Testing adding an invalid collection of scenes
     */
    public void testAddScene6(){
        int initialSize = 2;
        setUpScenes(initialSize);
        setupGame();
        List<Scene> scenes = null;
        try{
            lm.addScenes(scenes);
            fail("Should not be able to add a null collection.");
        }catch(IllegalArgumentException e){

        }
    }

    @Test
    /**
     * Testing adding an invalid collection of scenes
     */
    public void testAddScene7(){
        int initialSize = 2;
        setUpScenes(initialSize);
        setupGame();
        List<Scene> scenes = new ArrayList<>();
        try{
            lm.addScenes(scenes);
            fail("Should not be able to add an empty collection.");
        }catch(IllegalArgumentException e){

        }
    }

    private void setupGame(){
        Game g = new TestGame();
        lm.setGame(g);
    }
    
    private class TestScene extends Scene{
        @Override
        public void startScene() {

        }
    }

    private class TestGame extends Game{
        public TestGame(){
            super(lm,
                    new Renderer() {
                @Override
                public void setMask(int mask) {

                }

                @Override
                public void render(Scene scene) {

                }
            }, new InputBroadcaster() {
                @Override
                public void addKeyListener(KeyListener listener) {

                }

                @Override
                public void addMouseListener(MouseListener listener) {

                }
            });
        }
    }

}