package ivara;

import core.Game;
import core.components.ScriptComponent;
import core.components.TextComponent;
import core.entity.GameEntity;
import core.struct.Text;
import ivara.entities.TimerEntity;
import ivara.entities.scripts.TimerScript;
import ivara.scenes.DefaultScene;

import java.io.*;
import java.util.Collection;
import java.util.List;

import maths.Vector;
import scew.Component;

/**
 * Saving class that outputs the number of the level the player is currently on, the position where they would
 * spawn (taking into account checkpoints), the amount of time the timer has been going for, and the position of
 * the coins the player has collected.
 */
public class SaveScene {

    private static Game game;
    private static DefaultScene scene;
    private static File savefile = new File("./savefile.sav");

    /**
     * Save method that outputs the level number, the player's next spawn position, the timer and the collected coins to
     * a .sav file
     * @param g The current game instance
     */
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

            /**
            Collection<Component> timerComponents = timerToSave.getComponents();
            TextComponent tc = null;
            for(Component c :   timerComponents){
                if(c instanceof TextComponent){
                    tc = (TextComponent) c;
                }
            }


            List<Text> textList = tc.getTexts();
            bw.write(textList.get(0).text + " ");
            **/

            ScriptComponent scriptComp = timerToSave.get(ScriptComponent.class).get(); // We assume the timer has a script
            TimerScript ts = (TimerScript) scriptComp.getScripts().stream().findFirst().get(); // We assume the timer has a TimerScript

            bw.write(ts.start + " " + ts.currentTime+ " "); // writing the necessary info for the time

            for(GameEntity coin : coinCollection){
                Vector transformVector = coin.getTransform();
                bw.write(Float.toString(transformVector.x) + " ");
                bw.write(Float.toString(transformVector.y) + " ");
            }

            bw.close();
        }catch(Exception e){
            System.out.println(e.getMessage() + e.getCause());
        }
    }
}
