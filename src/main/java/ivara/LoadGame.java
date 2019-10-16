package ivara;

import core.Game;
import core.Script;
import core.components.ScriptComponent;
import core.entity.GameEntity;
import core.scene.Scene;
import ivara.entities.CoinEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.TimerEntity;
import ivara.scenes.DefaultScene;
import maths.Vector;
import util.Debug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    private static final String fileName = "./savefile.sav";

    /**
     * This method takes a given game and builds it from a save file.
     * @param game The game to load into.
     * @return A boolean stating whether the load was successful.
     */
    public static boolean load(Game game){
        File file = new File(fileName);
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            List<String> data = Arrays.asList(br.readLine().split(" ")); // The information in the file only exists on a single line

            // Get the data as an iterator
            Iterator<String> dataIterator = data.iterator();
            // Number of the level to open up
            int levelNum = Integer.parseInt(dataIterator.next());
            // Checkpoint position that the player should spawn at
            Vector spawnPos = new Vector(Float.parseFloat(dataIterator.next()), Float.parseFloat(dataIterator.next()));
            // Timer information
            long start = Long.parseLong(dataIterator.next());
            long currentTime = Long.parseLong(dataIterator.next());
            // Coins to remove
            List<Vector> coins = new ArrayList<Vector>();
            while(dataIterator.hasNext()){ // Assuming that if there is one item next in the file, that there is another following it (i.e. 2 items)
                coins.add(new Vector(Float.parseFloat(dataIterator.next()), Float.parseFloat(dataIterator.next())));
            }

            // Change to the new scene
            game.setCurrentScene(levelNum);

            // Get the targeted scene
            Scene scene = game.getScene(levelNum);
            if(!(scene instanceof DefaultScene)){Debug.log("Scene is not a DefaultScene, of which it needs to be.");return false;}
            DefaultScene loadedLevel = (DefaultScene)scene;

            //Set the spawn point
            loadedLevel.setSpawn(spawnPos);

            //Set the timer
            GameEntity t = loadedLevel.getEntity(TimerEntity.class);
            if(t != null){ // Check that there is a timer entity
                TimerEntity timer = (TimerEntity) t;
                Optional<ScriptComponent> opScriptComp = timer.get(ScriptComponent.class);
                ScriptComponent scriptComp;
                if(opScriptComp.isPresent()){ // Check that there is a script component
                    scriptComp = opScriptComp.get();
                    Optional<Script> opScript = scriptComp.getScripts().stream().filter((e) -> e.getClass() == TimerEntity.TimerScript.class).findFirst();
                    TimerEntity.TimerScript timerScript;
                    if(opScript.isPresent())timerScript = (TimerEntity.TimerScript) opScript.get(); // Check that there is a timer script
                    else{Debug.log("Timer should have had a TimerScript but didn't.");return false;}
                    timerScript.currentTime = currentTime;
                    timerScript.start = start;
                }else{Debug.log("Timer should have a script component but doesn't.");return false;}

            } else {Debug.log("There should be a Timer in the scene, but there wasn't.");return false;}

            //Set coins of the player
            GameEntity p = loadedLevel.getEntity(PlayerEntity.class);
            if(p != null){
                PlayerEntity player = (PlayerEntity)p;
                player.coinsCollected = coins.size();
                player.getTransform().setAs(spawnPos);
            }else {Debug.log("There should be a player in the scene, but there wasn't.");return false;}

            //Remove the already collected coins from the scene
            Collection<GameEntity> removeableCoins = loadedLevel.getEntities(CoinEntity.class).stream().filter((e) ->coins.contains(e.getTransform())).collect(Collectors.toSet()); // Get coins that have the same position as the ones that we are looking to delete from the scene
            loadedLevel.bankCoins(removeableCoins); // Update the already collected coins in the scene
            for(GameEntity e : removeableCoins)loadedLevel.removeEntityRegardless(e); // Remove the already collected coin entities from the scene
            fr.close();

        }catch(IOException e){
            Debug.log(e.toString());
            return false;
        }
        Ivara.getBackgroundTrack().stop();
        Ivara.getBackgroundTrack().play(true);
        Debug.log("Successfully loaded the file.");
        return true;
    }
}
