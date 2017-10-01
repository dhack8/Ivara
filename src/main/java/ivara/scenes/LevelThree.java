package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.*;
import maths.Vector;


/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class LevelThree extends Scene{

    public LevelThree(){
        addEntity(new BackgroundEntity(0f,0f));

        addEntity(new PlayerEntity(-1, 0));

        addEntity(new BasicBlockEntity(-1, 1, "dirt"));
        addEntity(new BasicBlockEntity(4, 1, "dirt"));
        addEntity(new NPlatformEntity(0,2,4,false));

        addEntity(new MovingBlockEntity(5f, 3f, 5f, 5f, "grass-top", 5f));


        for(int i = 6; i < 11; i++){
            addEntity(new BasicBlockEntity(i,5, "grass-top"));
        }

        setCamera(new Camera(new Vector(0,0), new Vector(32,18)));

    }
}
