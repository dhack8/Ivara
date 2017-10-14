package ivara;

import core.Game;
import core.entity.GameEntity;
import core.scene.LevelManager;
import core.scene.Scene;
import core.struct.Timer;
import ivara.entities.CoinEntity;
import ivara.entities.TimerEntity;

import java.io.File;
import java.util.Collection;

/**
 * Created by james on 12/10/2017.
 */
public class SaveScene {

    private static Game game;
    private static LevelManager levelManager;
    private static Scene scene;
    private static File file = new File("./savefile.sav");

    public static void save(Game g){
        game = g;
        levelManager = game.getLevelManager();
        scene = game.getCurrentScene();

        int sceneNumToSave = game.getCurrentSceneNum();

        Collection<GameEntity> timerCollection = scene.getEntities(TimerEntity.class);
        TimerEntity timerToSave = (TimerEntity) timerCollection.iterator().next();

        Collection<GameEntity> coinCollection = scene.getEntities(CoinEntity.class);
        


//save scene
        //spawn
        //timer
        //position spawn
        //         coins
        //                   level number
    }
}
