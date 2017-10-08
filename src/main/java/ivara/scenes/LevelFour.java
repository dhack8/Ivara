package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.LevelEndEntity;
import ivara.entities.PlatformEntity;
import ivara.entities.PlatformEntity;
import ivara.entities.PlayerEntity;
import maths.Vector;

/**
 * Created by Alex Mitchell on 17/09/2017.
 */
public class LevelFour extends Scene{
    public void startScene(){ // Todo figure out where the timing issues are coming in

        addEntity(new PlatformEntity(new Vector(0,0),new Vector(1,0), 1f)); // left -> right
        addEntity(new PlatformEntity(new Vector(0,1), new Vector(0,2), 1f)); // up -> down


        addEntity(new PlatformEntity(new Vector(6,0), new Vector(5,0), 1f)); // right -> left
        addEntity(new PlatformEntity(new Vector(6,1), new Vector(6,0), 1f)); // down -> up


        addEntity(new PlatformEntity(new Vector(9,5), new Vector(7,1), 1f)); // backward diag test



        addEntity(new PlatformEntity(new Vector(6,6)));
        addEntity(new PlayerEntity(6,5));
        setCamera(new Camera(new Vector(0,0), new Vector(32,18)));

        //todo added
        addEntity(new LevelEndEntity(5,6));

    }
}
