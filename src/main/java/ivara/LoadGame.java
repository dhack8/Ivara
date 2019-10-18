package ivara;

import core.Game;
import core.Script;
import core.components.ScriptComponent;
import core.entity.GameEntity;
import core.scene.LevelManager;
import core.scene.Scene;
import ivara.entities.CoinEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.TimerEntity;
import ivara.scenes.DefaultScene;
import maths.Vector;
import util.Debug;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class handles the loading of a game from a file.
 * Loading class that takes the number of the level the to load, the position where they would
 * spawn (taking into account checkpoints), the amount of time the timer has been going for, and the position of
 * the coins the player has collected.
 * @author Alex Mitchell
 */
public class LoadGame {
    private static final String fileName = "./savefile.ser";

    /**
     * This method takes a given game and builds it from a save file.
     * @return A boolean stating whether the load was successful.
     */
    public static void load(Game g){
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fin);
            Game loadedGame = (Game) in.readObject();
            in.close();
            fin.close();
            g = loadedGame;
            System.out.println("Loaded game");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static LevelManager load2(Game g){
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fin);
            LevelManager levelManager = (LevelManager) in.readObject();
            in.close();
            fin.close();
            System.out.println("Loaded game");
            levelManager.setGame(g);
            return levelManager;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
