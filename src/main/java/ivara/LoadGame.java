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
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

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
            List<String> strings = Arrays.asList(br.readLine().split(" "));

            Iterator<String> i = strings.iterator();
            int levelNum = Integer.parseInt(i.next());
            Vector spawnPos = new Vector(Float.parseFloat(i.next()), Float.parseFloat(i.next()));
            String time = i.next();
            List<Vector> coins = new ArrayList<Vector>();
            while(i.hasNext()){
                coins.add(new Vector(Float.parseFloat(i.next()), Float.parseFloat(i.next())));
            }


            lm.setScene(levelNum);//Todo moved
            //Set spawn point
            DefaultScene startScene = (DefaultScene) lm.getScene(levelNum);
            System.out.println("Level num: " + levelNum);
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
                System.out.println("Prev time: " + text.text + " New time: "+ time );
                textComponent.add(text.transform, time, text.fontSize);
            } else throw new RuntimeException("Could not find the textComponent.");

            //Set coins of the player
            GameEntity p = startScene.getEntity(PlayerEntity.class);
            if(p != null){
                PlayerEntity player = (PlayerEntity)p;
                player.coinsCollected = coins.size();
                player.getTransform().setAs(spawnPos);
            }else throw new RuntimeException("Could not find a player entity.");

            //Remove the coins from the scene
            System.out.println(coins.size());
            System.out.println("To delete");
            for(Vector v : coins){
                System.out.println(v);
            }

            System.out.println("All coins");
            Collection<GameEntity> removeableCoins = startScene.getEntities(CoinEntity.class).stream().filter((e) ->coins.contains(e.getTransform())).collect(Collectors.toSet());
            for(GameEntity entity : startScene.getEntities(CoinEntity.class)){
                System.out.println(entity.getTransform());
            }
            System.out.println("Need to remove: " + removeableCoins.size());
            startScene.bankCoins(removeableCoins);
            removeableCoins.stream().forEach((e)->startScene.removeEntityRegardless(e)); // remove the coin entities from the scene

            fr.close();


        }catch(Exception e){
            System.err.println(e);
            return false;
        }

        return true;
    }
}
