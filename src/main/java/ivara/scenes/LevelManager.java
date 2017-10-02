package ivara.scenes;

import core.Game;
import core.scene.Scene;
import ivara.Ivara;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Alex Mitchell
 */
public class LevelManager {

    private List<Scene> scenes;
    private int currentScene;

    public LevelManager(Scene s){
        if(s == null) throw new IllegalArgumentException("Cannot add a null scene.");
        scenes = new ArrayList<Scene>();
        scenes.add(s);
        currentScene = 0;

    }

    public LevelManager(List<Scene> levels){
        if(levels == null)throw new IllegalArgumentException("Cannot add a null collection of levels.");
        if(!(levels.size()>0)) throw new IllegalArgumentException("There must be at least one level.");
        scenes = levels;
        currentScene = 0;
    }



    public Scene getScene(int level){
        if(level >= scenes.size()) throw new NoSuchElementException("The scene does not exist.");
        return scenes.get(level);
    }

    public Scene getCurrentScene(){
        return scenes.get(currentScene);
    }

    /**
     * Loops back to the starting scene
     * Scene 0 is menu?
     * Final scene is credits?
     */
    public void nextScene(Game g){
        if(currentScene == scenes.size()-1)currentScene = 0;
        else currentScene++;
        getCurrentScene().setGame(g);
    }

    public void setScene(int level, Game g){
        if(level >= scenes.size()) throw new NoSuchElementException("The scene does not exist.");
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

}
