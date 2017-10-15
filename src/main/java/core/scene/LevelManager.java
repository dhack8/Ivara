package core.scene;

import core.Game;
import core.scene.Scene;
import ivara.Ivara;

import java.util.*;

/**
 * This class manages the Scenes/levels within a game.
 *
 * The LevelManager assumes that a game is set before the methods are run.
 * @author Alex Mitchell
 */
public class LevelManager {

    private Game game; // The game associated with this level manager
    private List<Scene> scenes; // Levels in the game, must have at least 1
    private Scene menu; // An optional pause menu

    private int currentScene; // Current scene number
    private boolean paused;


    /**
     * Creates a LevelManager with a single scene and no menu.
     * @param scene The initial scene in the game.
     * @throws NullPointerException On construction with a null Scene.
     */
    public LevelManager(Scene scene){
        if(scene == null) throw new NullPointerException("Cannot construct a LevelManager with a null Scene.");
        List<Scene>scenes = new ArrayList<Scene>();
        scenes.add(scene);
        setup(scenes, Optional.empty());
    }

       /**
     * Creates a LevelManager with a single scene and a menu.
     * @param scene The initial scene in the game.
     * @param menu The menu of the game.
     * @throws NullPointerException On construction with a null Scene or null Menu
     */
    public LevelManager(Scene scene, Scene menu){
        if(scene == null || menu == null) throw new NullPointerException("Cannot construct a LevelManager with a null Scene.");
        List<Scene>scenes = new ArrayList<Scene>();
        scenes.add(scene);
        setup(scenes, Optional.of(menu));
    }

    /**
     * Creates a LevelManager with a collection of scenes.
     * The collection must contain at least a single scene.
     * @param scenes A list of scenes in order of how they should be played.
     * @throws NullPointerException On a null collection of Scenes.
     * @throws IllegalArgumentException If there is not at least 1 Scene within the collection.
     */
    public LevelManager(List<Scene> scenes){
        if(scenes == null)throw new NullPointerException("Cannot add a null collection of Scenes.");
        if(!(scenes.size()>0)) throw new IllegalArgumentException("There must be at least one Scene in the collection.");
        setup(scenes, Optional.empty());
    }

    /**
     * Creates a LevelManager with a collection of scenes and a menu.
     * The collection must contain at least a single scene.
     * @param scenes A list of scenes in order of how they should be played.
     * @param menu A menu of the game.
     * @throws NullPointerException On a null scene.
     * @throws IllegalArgumentException If there is not at least 1 Scene within the collection.
     */
    public LevelManager(List<Scene> scenes, Scene menu){
        if(scenes == null || menu == null)throw new NullPointerException("Cannot construct a LevelManager with a null level.");
        if(!(scenes.size()>0)) throw new IllegalArgumentException("There must be at least one level.");
        setup(scenes, Optional.of(menu));
    }

    /**
     * Sets up the layout manager.
     * @param scenes The scenes.
     * @param menu The menu that may or may not exist.
     */
    private void setup(List<Scene> scenes, Optional<Scene> menu){
        this.scenes = scenes;
        currentScene = 0;
        if(menu.isPresent()){
            this.menu = menu.get();
        }
    }

    /**
     * Sets all the scenes in the LevelManager to reference the game.
     * @param g The game to reference.
     * @throws NullPointerException On a null Game.
     */
    public void setGame(Game g){
        if(g == null) throw new NullPointerException("Game cannot be null.");
        game = g;

        //Give back reference to all scenes
        for(Scene s: scenes){
            s.setGame(g);
        }
        if(menu != null)menu.setGame(g);
    }

    /**
     * Gets a specified Scene object given the number of scene.
     * @param scene The scene number.
     * @return The scene corresponding to the scene number.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     * @throws IndexOutOfBoundsException When the given index is out of the range of the array of scenes.
     */
    public Scene getScene(int scene){
        if(game == null) throw new NullPointerException("Must give the LevelManager a game before calling actions on it.");
        if(scene >= scenes.size() || scene < 0) throw new IndexOutOfBoundsException("The scene does not exist, out of bounds.");
        return scenes.get(scene);
    }

    /**
     * Gets the current scene in the game.
     * @return The current scene.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     */
    public Scene getCurrentScene(){
        if(game == null) throw new NullPointerException("Must give the level manager a game before calling actions on it.");
        return paused? menu : scenes.get(currentScene);
    }

    /**
     * Returns the current level number, regardless of whether the game is paused or not.
     * @return The integer corresponding to the level number.
     */
    public int getLevelNum(){
        return currentScene;
    }

    /**
     * Changes the current scene to the next scene.
     * Upon reaching the last scene, the next scene becomes the first scene.
     * Cannot be called while the game is paused.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     * @throws RuntimeException When changing scenes if the menu is open.
     */
    public void nextScene(){
        if(game == null) throw new NullPointerException("Must give the level manager a game before calling actions on it.");
        if(paused) throw new RuntimeException("Cannot change to the next scene while the menu is open.");

        getCurrentScene().resetScene(); // Reset the current scene on exit

        if(currentScene == scenes.size()-1)currentScene = 0;
        else currentScene++;
    }

    /**
     * Sets the current scene to a scene specified by the scene number.
     * @param scene The number of the scene.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     * @throws IndexOutOfBoundsException When the scene number is out of the bounds of the current array.
     */
    public void setScene(int scene){
        if(game == null) throw new NullPointerException("Must give the scene manager a game before calling actions on it.");
        if(scene >= scenes.size() || scene < 0) throw new IndexOutOfBoundsException("The scene does not exist, out of bounds.");
        paused = false;

        getCurrentScene().resetScene(); // reset the current scene on exit

        currentScene = scene;
    }

    /**
     * Add a scene to the end of the collection.
     * @param scene The scene to add.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     */
    public void addScene(Scene scene){
        if(scene == null) throw new NullPointerException("Must give the LevelManager a game before calling actions on it.");
        scenes.add(scene);
        scene.setGame(game);
    }

    /**
     * Adds a scene at a specific position in the collection.
     * @param scene The scene to add.
     * @param sceneNum The number of the scene.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     * @throws IndexOutOfBoundsException When the scene number is out of the bounds of the current array.
     *
     */
    public void addScene(Scene scene, int sceneNum){
        if(scene == null) throw new NullPointerException("Must give the scene manager a game before calling actions on it.");
        if(sceneNum < 0 || sceneNum > scenes.size()) throw new IndexOutOfBoundsException("The scene position is out of bounds of the current collection of scenes.");
        scenes.add(sceneNum, scene);
        scene.setGame(game);
    }

    /**
     * Appends a collection of scenes to the current collection of scenes.
     * @param scenes The collection of scenes.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     * @throws IllegalArgumentException There must be at least one scene in the collection.
     */
    public void addScenes(Collection<Scene> scenes){
        if(scenes == null) throw new NullPointerException("Must give the scene manager a game before calling actions on it.");
        if(!(scenes.size()>0)) throw new IllegalArgumentException("There must be at least one scene.");
        for(Scene sc : scenes)sc.setGame(game);
        this.scenes.addAll(scenes);
    }

    /**
     * Sets a pause menu scene for a game.
     * @param pause The scene to use as a pause menu.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     */
    public void setPauseMenu(Scene pause){
        if(pause == null) throw new NullPointerException("Must give the scene manager a game before calling actions on it.");
        menu = pause;
        menu.setGame(game);
    }

    /**
     * Returns the pause Scene
     * @return The pause Scene, null if it doesn't exist.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     */
    public Scene getPauseMenu(){
        if(game == null) throw new NullPointerException("Must give the level manager a game before calling actions on it.");
        return menu;
    }

    /**
     * Pauses the game / switches to the pause menu if it exists.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     * @throws RuntimeException When pause is called and there is no pause menu.
     */
    public void pause(){
        if(game == null) throw new NullPointerException("Must give the level manager a game before calling actions on it.");
        if(menu == null) throw new RuntimeException("Cannot pause when no menu exists.");
        paused = !paused;
    }

}
