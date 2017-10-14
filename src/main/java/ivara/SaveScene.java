package ivara;

import core.Game;
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
    private static File file = new File("./savefile.sav");

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
        Collection<GameEntity> coinCollection = scene.getEntities(CoinEntity.class);

        try {
            Writer bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./savefile.sav"), "utf-8"));
            bw.write(sceneNumToSave);
            bw.write(spawn.toString());
            bw.write(timerToSave.toString());

            bw.write("//coins now//");
            for(GameEntity ge : coinCollection){
                bw.write(ge.toString());
            }

        }catch(Exception e){

        }

    }
}
