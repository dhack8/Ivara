package pxljam.scenes;

import core.scene.Scene;
import pxljam.entities.*;

/**
 * Our very first scene.
 *
 * @author David Hack
 * @author Alex Mitchell
 */
public class LevelOne extends Scene {

    public LevelOne() {
        //addEntity(new BackgroundEntity(0,0));
        addEntity(new PlayerEntity(2, 1.5f));

        addEntity(new NPlatformEntity(1,2,3,false));
        addEntity(new NPlatformEntity(4,3, 3, true));
        addEntity(new NPlatformEntity(6,9, 3, false));

        //steps
        addEntity(new NPlatformEntity(9,8, 1, false));
        addEntity(new NPlatformEntity(11,7, 1, false));
        addEntity(new NPlatformEntity(13,6, 1, false));

        //vertical platforms
        addEntity(new NPlatformEntity(15,1, 4, true));
        addEntity(new NPlatformEntity(12,0  , 2, true));
        addEntity(new NPlatformEntity(15,-3  , 2, true));
        addEntity(new NPlatformEntity(12,-7  , 3, true));

        addEntity(new NPlatformEntity(10,-8  , 2, false));
        addEntity(new NPlatformEntity(4,-9  , 3, false));
        //addEntity(new MovingBlockEntity(0,-9, 2,-9, "dirt_bottom", 2));
    }
}
