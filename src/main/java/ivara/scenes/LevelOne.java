package ivara.scenes;

import core.scene.Scene;
import core.struct.Camera;
import ivara.entities.*;
import maths.Vector;

/**
 * Level one, introduces moving platform (horizontal and vertical) to the player.
 * @author James Magallanes
 */
public class LevelOne extends Scene {

    public LevelOne() {

        /*
        //addEntity(new BackgroundEntity(0,0));
        addEntity(new PlayerEntity(2, 1.5f));

        //addEntity(new MovingBlockEntity(5f, 3f, 5f, 5f, "grass-top", 0.4f));
        addEntity(new MovingBlockEntity(5f, 3f, 10f, 3f, "grass-top", 1.5f));

        addEntity(new NPlatformEntity(1,2,3,false));
        addEntity(new NPlatformEntity(4,3, 3, true));
        addEntity(new NPlatformEntity(6,9, 3, false));

        //steps
        //addEntity(new NPlatformEntity(9,8, 1, false));
        //addEntity(new NPlatformEntity(11,7, 1, false));
        //addEntity(new NPlatformEntity(13,6, 1, false));

        //vertical platforms
        addEntity(new NPlatformEntity(15,1, 4, true));
        addEntity(new NPlatformEntity(12,0  , 2, true));
        addEntity(new NPlatformEntity(15,-3  , 2, true));
        addEntity(new NPlatformEntity(12,-7  , 3, true));

        addEntity(new NPlatformEntity(10,-8  , 2, false));
        addEntity(new NPlatformEntity(3,-9  , 3, false));
        //addEntity(new MovingBlockEntity(0f,-9f, 2f,-9f, "dirt_bottom", 2f));

        */
        addEntity(new BackgroundEntity(0,0));
        addEntity(new PlayerEntity(0, 1.5f));

        addEntity(new NPlatformEntity(0,3,2,false));
        addEntity(new MovingBlockEntity(3, 3, 8, 3, "grass-top", 2.5f));
        addEntity(new NPlatformEntity(10,3,2,false));
        addEntity(new MovingBlockEntity(13,4,13,-4, "grass-top", 2.5f));
        addEntity(new NPlatformEntity(15,-4,3,false));

        //fall catcher
        addEntity(new NPlatformEntity(2,11,9,false));
        addEntity(new NPlatformEntity(11,7,5,true));
        //addEntities(LinePlatformFactory.line(-1,8,1,10));
        addEntity(new MovingBlockEntity(-1,8,-1,3, "grass-top", 3));
        addEntity(new NPlatformEntity(-5,2,7,true));
        addEntity(new NPlatformEntity(-4,8,4,false));


        //todo added
        addEntity(new LevelEndEntity(2,1));

        setCamera(new Camera());
    }
}
