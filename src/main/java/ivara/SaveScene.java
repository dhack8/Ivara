package ivara;

import core.Game;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.scene.LevelManager;
import core.scene.Scene;
import core.struct.Timer;
import ivara.entities.CoinEntity;
import ivara.entities.TimerEntity;
import ivara.scenes.DefaultScene;

import java.io.*;
import java.util.Collection;
import maths.Vector;

/**
 * Created by james on 12/10/2017.
 */
public class SaveScene {

    private static Game game;
    private static LevelManager levelManager;
    private static DefaultScene scene;
    private static File savefile = new File("./savefile.sav");

    public static void save(Game g){
        game = g;
        levelManager = game.getLevelManager();

        //level number
        int sceneNumToSave = game.getCurrentSceneNum();
        scene = (DefaultScene) game.getScene(sceneNumToSave);
        //spawn position
        Vector spawn = scene.getSpawnVector();
        //time
        TimerEntity timerToSave = (TimerEntity) scene.getEntity(TimerEntity.class);
        //coin positions - new method added to default scene
        Collection<GameEntity> coinCollection = scene.getCollectedCoins();

        try {
            FileWriter fw = new FileWriter(savefile);
            Writer bw = new BufferedWriter(fw);

            bw.write(sceneNumToSave + " ");
            bw.write(Float.toString(spawn.x) + " ");
            bw.write(Float.toString(spawn.y) + " ");
            bw.write(timerToSave.get(TextComponent.class). + " ");

            bw.write("//coins now//");
            for(GameEntity ge : coinCollection){
                bw.write(ge.toString());
            }

            bw.close();

        }catch(Exception e){

        }


    }
}
