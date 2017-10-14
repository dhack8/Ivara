package ivara;

import core.Game;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.Text;
import ivara.entities.TimerEntity;
import ivara.scenes.DefaultScene;

import java.io.*;
import java.util.Collection;
import java.util.List;

import maths.Vector;
import scew.Component;

/**
 * Created by james on 12/10/2017.
 */
public class SaveScene {

    private static Game game;
    private static DefaultScene scene;
    private static File savefile = new File("./savefile.sav");

    public static void save(Game g){
        game = g;

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

            Collection<Component> col = timerToSave.getComponents();
            TextComponent tc = null;
            for(Component c : col){
                if(c instanceof TextComponent){
                    tc = (TextComponent) c;
                }
            }

            List<Text> textList = tc.getTexts();
            bw.write(textList.get(0).text + " ");

            for(GameEntity ge : coinCollection){
                Vector transformVector = ge.getTransform();
                bw.write(Float.toString(transformVector.x) + " ");
                bw.write(Float.toString(transformVector.y) + " ");
            }

            bw.close();
        }catch(Exception e){

        }


    }
}
