package ivara;

import core.Game;
import core.Script;
import core.components.ScriptComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.scene.LevelManager;
import core.scene.Scene;
import core.struct.Text;
import core.struct.Timer;
import ivara.entities.TimerEntity;
import ivara.scenes.DefaultScene;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import maths.Vector;
import scew.Component;
import util.Debug;

/**
 * Saving class that outputs the number of the level the player is currently on, the position where they would
 * spawn (taking into account checkpoints), the amount of time the timer has been going for, and the position of
 * the coins the player has collected.
 * @author James Magallanes
 */
public class SaveGame {
    private static final String savefile = "./savefile.ser";

    /**
     * Save method that outputs the level number, the player's next spawn position, the timer and the collected coins to
     * a .sav file
     */
    public static void save(LevelManager levelManager){
        try {
            FileOutputStream fout = new FileOutputStream(savefile);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(levelManager);
            out.close();
            fout.close();
            System.out.println("Saved model to: " + savefile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
