package ivara.scenes;

import core.Game;
import core.scene.Scene;
import ivara.Ivara;

import java.util.*;

/**
 * @author Alex Mitchell
 *
 * This class manages the levels within a game
 */
public class LevelManager {

    private List<Scene> scenes;
    private int currentScene;
    private boolean paused;
    private Scene pauseMenu;

    /**
     * Creates a LevelManager with a single scene
     * @param s The initial scene in the game
     */
    public LevelManager(Scene s){
        if(s == null) throw new IllegalArgumentException("Cannot add a null scene.");
        scenes = new ArrayList<Scene>();
        scenes.add(s);
        //scenes.add(Objects.requireNonNull(s, "Cannot add a null scene."));
        currentScene = 0;
        paused = false;
    }

    /**
     * Creates a LevelManager with a collection of scenes
     * The collection must contain at least a single scene
     * @param levels A list of levels in order of how they should be played
     */
    public LevelManager(List<Scene> levels){
        if(levels == null)throw new IllegalArgumentException("Cannot add a null collection of levels.");
        if(!(levels.size()>0)) throw new IllegalArgumentException("There must be at least one level.");
        scenes = levels;
        currentScene = 0;
        paused = false;
    }

    /**
     * Gets a specified Scene object given the number of scene
     * @param level The level number
     * @return The scene corresponding to the level number
     */
    public Scene getScene(int level){
        if(level >= scenes.size()) throw new NoSuchElementException("The scene does not exist.");
        return scenes.get(level);
    }

    /**
     * Gets the current scene in the game
     * @return The current scene
     */
    public Scene getCurrentScene(){
        return paused? pauseMenu : scenes.get(currentScene);
    }

    /**     *
     * Changes the current scene to the next scene
     * Upon reaching the last scene, the next scene becomes the first scene
     * Scene 0 is expected to be the menu
     *
     * @param g A reference to the game in order to set a back reference.
     */
    public void nextScene(Game g){ // Todo change this as the passed in game is only for giving a back-reference
        if(paused) throw new RuntimeException("Cannot change to the next scene while the menu is open.");
        if(currentScene == scenes.size()-1)currentScene = 0;
        else currentScene++;
        getCurrentScene().setGame(g);
    }

    /**
     * Sets the current scene to a scene specified by the level number
     * @param level The number of the level
     * @param g A reference to the game in order to set a back reference
     */
    public void setScene(int level, Game g){ // Todo decide if game should be here, or add a check if g == null
        if(level >= scenes.size()) throw new NoSuchElementException("The scene does not exist.");
        paused = false;
        currentScene = level;
        getCurrentScene().setGame(g);
    }


    /**
     * Adds a scene to the end of the collection
     * @param s The scene to add
     */
    public void addScene(Scene s){
        if(s == null) throw new IllegalArgumentException("Cannot add a null scene.");
        scenes.add(s);
    }

    /**
     * Adds a scene at a specific position in the collection
     * @param s The scene to add
     * @param level The number of the level/scene
     */
    public void addScene(Scene s, int level){
        if(s == null) throw new IllegalArgumentException("Cannot add a null scene.");
        if(level < 0 || level > scenes.size()) throw new IllegalArgumentException("Out of bounds of the collection");
        scenes.add(level, s);
    }

    /**
     * Appends a collection of scenes to the current collection of scenes
     * @param s The collection of scenes
     */
    public void addScenes(Collection<Scene> s){
        if(s == null) throw new IllegalArgumentException("Cannot add a null collection.");
        if(!(s.size()>0)) throw new IllegalArgumentException("There must be at least one level.");
        scenes.addAll(s);
    }

    /**
     * Appends a collection of scenes to the current collection of scenes
     * @param s The collection of scenes
     */
    /**
    public void addScenes(Collection<Scene> s){
        scenes.addAll(Objects.requireNonNull(s, "Cannot add a null collection."));
    }
     **/

    /**
     * Sets a pause menu scene for a game
     * By default, there is no pause menu
     * @param pause The scene to use as a pause menu
     */
    public void setPauseMenu(Scene pause){
        if(pause == null) throw new IllegalArgumentException("Cannot set a null pause menu.");
        pauseMenu = pause;
    }

    /**
     * Pauses the game / switches to the pause menu
     * @param g A reference to the game, used for backwards reference
     */
    public void pause(Game g){
        if(pauseMenu == null) throw new RuntimeException("Cannot pause when no menu exists.");
        paused = !paused;
        getCurrentScene().setGame(g);
    }


    //Todo NOTE
    /**
     * At this current stage, a LevelManager must have at least 1 scene
     * A pause menu is optional, and must be set after the construction of the LevelManager
     */

}
