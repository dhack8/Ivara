package ivara.scenes;

import core.scene.Scene;
import ivara.entities.*;

/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class LevelThree extends Scene{

    public LevelThree(){
        addEntity(new BackgroundEntity(-5f,-5f));

        addEntity(new PlayerEntity(-1, 0));

        addEntity(new BasicBlockEntity(-1, 1, "dirt"));
        addEntity(new BasicBlockEntity(4, 1, "dirt"));
        addEntity(new NPlatformEntity(0,2,4,false));

        addEntity(new BasicEnemyEntity(3, 0));
    }
}
