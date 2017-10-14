package ivara;

import core.Game;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.scene.LevelManager;
import core.scene.Scene;
import core.struct.Text;
import core.struct.Timer;
import ivara.entities.CoinEntity;
import ivara.entities.PlayerEntity;
import ivara.entities.TimerEntity;
import ivara.scenes.DefaultScene;
import maths.Vector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Alex Mitchell on 14/10/2017.
 */
public class LoadGame {
    public static final String fileName = "./savefile.sav";

    public static boolean load(Game game){
        LevelManager lm = game.getLevelManager();
        File file = new File(fileName);
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            Iterator<String> i = br.lines().iterator();

            int levelNum = Integer.parseInt(i.next());
            Vector spawnPos = new Vector(Float.parseFloat(i.next()), Float.parseFloat(i.next()));
            String time = i.next();
            Set<Vector> coins = new HashSet<>();
            while(i.hasNext()){
                coins.add(new Vector(Float.parseFloat(i.next()), Float.parseFloat(i.next())));
            }

            //Set spawn point
            DefaultScene startScene = (DefaultScene) lm.getScene(levelNum);
            startScene.setSpawn(spawnPos);

            //Set the timer
            GameEntity t = startScene.getEntity(TimerEntity.class);
            if(t != null){
                TimerEntity timer = (TimerEntity) t;
                TextComponent textComponent = timer.get(TextComponent.class).get(); // timer entity should have a text component
                Optional<Text> opText = textComponent.getTexts().stream().findFirst();
                Text text;
                if(opText.isPresent())text = opText.get();
                else throw new RuntimeException("Could not find text in the timer when saving.");
                textComponent.clear(); // clear the current texts
                textComponent.add(text.transform, time, text.fontSize);
            } else throw new RuntimeException("Could not find the textComponent.");

            //Set coins of the player
            GameEntity p = startScene.getEntity(PlayerEntity.class);
            if(p != null){
                PlayerEntity player = (PlayerEntity)p;
                player.coinsCollected = coins.size();
            }else throw new RuntimeException("Could not find a player entity.");

            //Remove the coins from the scene
            startScene.getEntities(CoinEntity.class).stream().filter((e) ->
                    coins.contains(e.getTransform())).forEach((e)->startScene.removeEntity(e)); // remove the coin entities from the scene

            fr.close();
        }catch(Exception e){
            System.err.println(e);
            return false;
        }

        return true;
    }
}
