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

        addEntity(new PlatformEntity(new Vector(-1, 1)));
        addEntity(new PlatformEntity(new Vector(4,1)));
        addEntity(new PlatformEntity(new Vector(0,2),4,false));

        addEntity(new PlatformEntity(new Vector(5,3), new Vector(5,5), 5f));


        for(int i = 6; i < 11; i++){
            addEntity(new PlatformEntity(new Vector(i, 5)));
        }

        setCamera(new Camera());

        //todo added
        addEntity(new LevelEndEntity(2,0));
    }
}
