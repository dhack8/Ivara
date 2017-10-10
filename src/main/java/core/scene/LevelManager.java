package core.scene;

import core.Game;
import core.scene.Scene;
import ivara.Ivara;

import java.util.*;

/**
 * @author Alex Mitchell
 *
 * This class manages the levels within a game
 *
 * The LevelManager assumes that a game is set before the methods are run
 */
public class LevelManager {

    private Game game; // for back reference

    private List<Scene> scenes; // levels in the game, must have at least 1
    private Scene menu; // can have a pause menu

    private int currentScene;
    private boolean paused;


    /**
     * Creates a LevelManager with a single scene and no menu
     * @param level The initial scene in the game
     */
    public LevelManager(Scene level){
        if(level == null) throw new IllegalArgumentException("Cannot add a null level.");
        scenes = new ArrayList<Scene>();
        scenes.add(level);
        setup(scenes, Optional.empty());
    }

    /**
     * Creates a LevelManager with a single scene and a menu
     * @param level The initial scene in the game
     * @param menu The menu of the game
     */
    public LevelManager(Scene level, Scene menu){
        if(level == null || menu == null) throw new IllegalArgumentException("Cannot add a null level.");
        scenes = new ArrayList<Scene>();
        scenes.add(level);
        setup(scenes, Optional.of(menu));
    }

    /**
     * Creates a LevelManager with a collection of scenes
     * The collection must contain at least a single scene
     * @param levels A list of levels in order of how they should be played
     */
    public LevelManager(List<Scene> levels){
        if(levels == null)throw new IllegalArgumentException("Cannot add a null collection of levels.");
        if(!(levels.size()>0)) throw new IllegalArgumentException("There must be at least one level.");
        setup(levels, Optional.empty());
    }

    /**
     * Creates a LevelManager with a collection of scenes and a menu
     * The collection must contain at least a single scene
     * @param levels A list of levels in order of how they should be played
     * @param menu A menu of the game
     */
    public LevelManager(List<Scene> levels, Scene menu){
        if(levels == null || menu == null)throw new IllegalArgumentException("Cannot add a null level.");
        if(!(levels.size()>0)) throw new IllegalArgumentException("There must be at least one level.");
        setup(levels, Optional.of(menu));
    }

    /**
     * Sets up the layout manager
     * @param levels The levels
     * @param menu The menu that may or may not exist
     */
    private void setup(List<Scene> levels, Optional<Scene> menu){
        scenes = levels;
        currentScene = 0;
        if(menu.isPresent()){
            this.menu = menu.get();
            paused = true;
        }
    }

    /**
     * Sets all the scenes in the game referencing the game
     * @param g The game to reference
     */
    public void setGame(Game g){
        if(g == null) throw new IllegalArgumentException("Game cannot be null.");
        game = g;

        //Give back reference to all scenes
        for(Scene s: scenes){
            s.setGame(g);
        }
        if(menu != null)menu.setGame(g);
    }

    /**
     * Gets a specified Scene object given the number of scene
     * @param level The level number
     * @return The scene corresponding to the level number
     */
    public Scene getScene(int level){
        if(game == null) throw new IllegalArgumentException("Must give the level manager a game before calling actions on it.");
        if(level >= scenes.size()) throw new NoSuchElementException("The scene does not exist.");
        return scenes.get(level);
    }

    /**
     * Gets the current scene in the game
     * @return The current scene
     */
    public Scene getCurrentScene(){
        if(game == null) throw new IllegalArgumentException("Must give the level manager a game before calling actions on it.");
        return paused? menu : scenes.get(currentScene);
    }

    /**
     * Changes the current scene to the next scene
     * Upon reaching the last scene, the next scene becomes the first scene
     *
     */
    public void nextScene(){
        if(game == null) throw new IllegalArgumentException("Must give the level manager a game before calling actions on it.");
        if(paused) throw new RuntimeException("Cannot change to the next scene while the menu is open.");

        getCurrentScene().resetScene(); // reset the current scene on exit

        if(currentScene == scenes.size()-1)currentScene = 0;
        else currentScene++;
    }

    /**
     * Sets the current scene to a scene specified by the level number
     * @param level The number of the level
     */
    public void setScene(int level){
        if(game == null) throw new IllegalArgumentException("Must give the level manager a game before calling actions on it.");
        if(level >= scenes.size()) throw new NoSuchElementException("The scene does not exist.");
        paused = false;

        getCurrentScene().resetScene(); // reset the current scene on exit

        currentScene = level;
    }

    /**
     * Adds a scene to the end of the collection
     * @param s The scene to add
     */
    public void addScene(Scene s){
        if(game == null) throw new IllegalArgumentException("Must give the level manager a game before calling actions on it.");
        if(s == null) throw new IllegalArgumentException("Cannot add a null scene.");
        scenes.add(s);
        s.setGame(game);
    }

    /**
     * Adds a scene at a specific position in the collection
     * @param s The scene to add
     * @param level The number of the level/scene
     */
    public void addScene(Scene s, int level){
        //if(game == null) throw new IllegalArgumentException("Must give the level manager a game before calling actions on it.");
        if(s == null) throw new IllegalArgumentException("Cannot add a null scene.");
        if(level < 0 || level > scenes.size()) throw new IllegalArgumentException("Out of bounds of the collection");
        scenes.add(level, s);
        s.setGame(game);
    }

    /**
     * Appends a collection of scenes to the current collection of scenes
     * @param s The collection of scenes
     */
    public void addScenes(Collection<Scene> s){
        //if(game == null) throw new IllegalArgumentException("Must give the level manager a game before calling actions on it.");
        if(s == null) throw new IllegalArgumentException("Cannot add a null collection.");
        if(!(s.size()>0)) throw new IllegalArgumentException("There must be at least one level.");
        for(Scene sc : s)sc.setGame(game);
        scenes.addAll(s);
    }

    /**
     * Sets a pause menu scene for a game
     * @param pause The scene to use as a pause menu
     */
    public void setPauseMenu(Scene pause){
        //if(game == null) throw new IllegalArgumentException("Must give the level manager a game before calling actions on it.");
        if(pause == null) throw new IllegalArgumentException("Cannot set a null pause menu.");
        paused = true; // will pause on add of pause menu
        menu = pause;
        menu.setGame(game);
    }

    /**
     * Returns the pause Scene
     * @return The pause Scene
     */
    public Scene getPauseMenu(){
        if(game == null) throw new IllegalArgumentException("Must give the level manager a game before calling actions on it.");
        return menu;
    }

    /**
     * Pauses the game / switches to the pause menu
     */
    public void pause(){
        if(game == null) throw new IllegalArgumentException("Must give the level manager a game before calling actions on it.");
        if(menu == null) throw new RuntimeException("Cannot pause when no menu exists.");
        paused = !paused;
    }

}
