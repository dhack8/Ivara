package ivara;

import core.Game;
import core.Script;
import core.components.ScriptComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
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
    private static final File savefile = new File("./savefile.sav");

    /**
     * Save method that outputs the level number, the player's next spawn position, the timer and the collected coins to
     * a .sav file
     * @param game The current game instance
     */
    public static boolean save(Game game){
        // Level number
        int sceneNumToSave = game.getCurrentSceneNum();

        // Scene to save
        Scene currentScene = game.getScene(sceneNumToSave);
        DefaultScene scene;
        if(currentScene != null) scene = (DefaultScene) currentScene;
        else {Debug.log("The scene to save must be a default scene.");return false;} // Fails if the scene is not a default scene (default scene has things that we want to save)

        // Spawn position
        Vector spawn = scene.getSpawnVector();

        // Timer to save in the current game
        GameEntity result = scene.getEntity(TimerEntity.class);
        TimerEntity timer;
        if(result != null)timer = (TimerEntity) result;
        else {Debug.log("A TimerEntity did not exist in the scene, when it should have.");return false;} // Fails if there is no timer in the scene

        // Coins collected in the current game
        Collection<GameEntity> coinCollection = scene.getCollectedCoins();

        try {
            FileWriter fw = new FileWriter(savefile);
            Writer bw = new BufferedWriter(fw);

            // Level number
            bw.write(sceneNumToSave + " ");
            // Player checkpoint location
            bw.write(Float.toString(spawn.x) + " ");
            bw.write(Float.toString(spawn.y) + " ");

            // Get the ScriptComponent of the timer if it exists
            Optional<ScriptComponent> opScriptComp = timer.get(ScriptComponent.class);
            ScriptComponent scriptComponent;
            if(opScriptComp.isPresent()){
                scriptComponent = opScriptComp.get();
            }else {Debug.log("Timer should have a script component, but doesn't.");return false;}

            // Get the TimerScript if it exists
            Optional<Script> opScript = scriptComponent.getScripts().stream().filter((e) -> e.getClass() == TimerEntity.TimerScript.class).findFirst();
            TimerEntity.TimerScript timerScript;
            if(opScript.isPresent()) timerScript = (TimerEntity.TimerScript) opScript.get();
            else {Debug.log("The timer should have had a timer script, but doesn't.");return false;}

            // Timer information
            bw.write(timerScript.start + " " + timerScript.currentTime+ " ");

            //Coin locations
            for(GameEntity coin : coinCollection){
                Vector transformVector = coin.getTransform();
                bw.write(Float.toString(transformVector.x) + " ");
                bw.write(Float.toString(transformVector.y) + " ");
            }

            bw.close();
        }catch(IOException e){
            Debug.log(e.toString());
        }

        Debug.log("Save successful.");
        return true;
    }
}
