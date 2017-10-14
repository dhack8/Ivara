package ivara;

import core.entity.GameEntity;
import core.scene.LevelManager;
import core.scene.Scene;
import ivara.entities.PlayerEntity;
import ivara.scenes.DefaultScene;
import maths.Vector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Alex Mitchell on 14/10/2017.
 */
public class LoadGame {
    public static final String fileName = "./savefile.sav";

    public static boolean load(LevelManager lm){
        File file = new File(fileName);

        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            Iterator<String> i = br.lines().iterator();
            int levelNum = Integer.parseInt(i.next());
            Vector spawnPos = new Vector(Float.parseFloat(i.next()), Float.parseFloat(i.next()));
            float time = Float.parseFloat(i.next());
            Set<Vector> coins = new HashSet<>();
            while(i.hasNext()){
                coins.add(new Vector(Float.parseFloat(i.next()), Float.parseFloat(i.next())));
            }

            //Set spawn point
            DefaultScene startScene = (DefaultScene) lm.getScene(levelNum);
            startScene.setSpawn(spawnPos);

            //Set

            //Set coins of the player
            GameEntity p = startScene.getEntity(PlayerEntity.class);
            if(p != null){
                PlayerEntity player = (PlayerEntity)p;
                player.coinsCollected = coins.size();
            }

        }catch(IOException e){
            System.err.println(e);
            return false;
        }
        return true;
    }
}
