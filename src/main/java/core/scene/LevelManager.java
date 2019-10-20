package core.scene;

import core.Game;
import core.scene.Scene;
import ivara.Ivara;

import java.io.Serializable;
import java.util.*;

/**
 * This class manages the Scenes/levels within a game.
 *
 * The LevelManager assumes that a game is set before the methods are run.
 * @author Alex Mitchell
 */
public class LevelManager implements Serializable {

    private transient Game game; // The game associated with this level manager
    private Scene menu; // An optional pause menu
    private Scene currentScene; // Current scene number
    private transient boolean paused;
    private Map<String, Scene> bookmarkedScenes = new HashMap<>();

    //TODO: Update comments from me changes
    /**
     * Creates a LevelManager with a single scene and no menu.
     * @param firstScene The initial scene in the game.
     * @throws NullPointerException On construction with a null Scene.
     */
    public LevelManager(Scene firstScene){
        if(firstScene == null) throw new NullPointerException("Cannot construct a LevelManager with a null Scene.");
        this.currentScene = firstScene;
    }

       /**
     * Creates a LevelManager with a single scene and a menu.
     * @param firstScene The initial scene in the game.
     * @param menu The menu of the game.
     * @throws NullPointerException On construction with a null Scene or null Menu
     */
    public LevelManager(Scene firstScene, Scene menu){
        if(firstScene == null || menu == null) throw new NullPointerException("Cannot construct a LevelManager with a null Scene.");
        this.currentScene = firstScene;
        this.menu = menu;
    }

    /**
     * Gets the current scene in the game.
     * @return The current scene.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     */
    public Scene getCurrentScene(){
        return paused? menu : currentScene;
    }

    public Scene getCurrentActiveScene(){
        return currentScene;
    }

    /**
     * Sets all the scenes in the LevelManager to reference the game.
     * @param g The game to reference.
     * @throws NullPointerException On a null Game.
     */
    public void setGame(Game g){
        if(g == null) throw new NullPointerException("Game cannot be null.");
        game = g;
        currentScene.setGame(g);
        if(menu != null) menu.setGame(g);
    }

    /**
     * Sets the current scene to a scene specified by the scene number.
     * @param scene The number of the scene.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     * @throws IndexOutOfBoundsException When the scene number is out of the bounds of the current array.
     */
    public void setScene(Scene scene){
        paused = false;
        scene.setGame(game);
        currentScene = scene;
    }

    public void bookmarkScene(String bookmark, Scene scene){
        bookmarkedScenes.put(bookmark, scene);
    }

    public Scene getBookmarkedScene(String bookmark){
        return bookmarkedScenes.get(bookmark);
    }

    public void setToBookmarkedScene(String bookmark){
        setScene(getBookmarkedScene(bookmark));
    }

    /**
     * Pauses the game / switches to the pause menu if it exists.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     * @throws RuntimeException When pause is called and there is no pause menu.
     */
    public void pause(){
        if(menu == null) throw new RuntimeException("Cannot pause when no menu exists.");
        paused = !paused;
    }

    /**
     * Returns the pause Scene
     * @return The pause Scene, null if it doesn't exist.
     * @throws NullPointerException When the game is null, preventing getting scenes that have null games.
     */
    public Scene getPauseMenu(){
        return menu;
    }
}
