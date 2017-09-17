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
        addEntity(new NPlatformEntity(10,8, 1, false));
    }
}
